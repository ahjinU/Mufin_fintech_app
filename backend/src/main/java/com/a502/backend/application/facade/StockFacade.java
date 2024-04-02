package com.a502.backend.application.facade;

import com.a502.backend.application.entity.*;
import com.a502.backend.domain.parking.ParkingDetailsService;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.stock.*;
import com.a502.backend.domain.stock.request.StockNameRequest;
import com.a502.backend.domain.stock.request.StockTransactionRequest;
import com.a502.backend.domain.stock.response.*;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class StockFacade {

	private final UserService userService;
	private final StocksService stocksService;
	private final StockBuysService stockBuysService;
	private final StockSellsService stockSellsService;
	private final ParkingService parkingService;
	private final StockDetailsService stockDetailsService;
	private final StockHoldingsService stockHoldingsService;
	private final ParkingDetailsService parkingDetailsService;
	private final CodeService codeService;
	private final RankService rankService;

	/**
	 * 매수 거래 신청 메서드
	 *
	 * @param request
	 */
	@Transactional
	public void stockBuy(StockTransactionRequest request) {
		String name = request.getName();
		int price = request.getPrice();
		int cnt_total = request.getCnt_total();
		User user = userService.userFindByEmail();
		Stock stock = stocksService.findByName(name);

		stockDetailsService.validStockPrice(stock, price);
		int price1 = parkingService.getParkingBalance(user);
		int price2 = stockBuysService.getStockBuyWaitingList(user, stock, codeService.findByName("거래중"));
		if (price1 - price2 < price * cnt_total)
			throw BusinessException.of(ErrorCode.API_ERROR_PARKING_NOT_ENOUGH_BALANCE);

		log.info("거래 가능 상태 확인 완료!Buy");

		Code code = codeService.findByName("거래중");

		StockBuy stockBuy = stockBuysService.save(user, stock, price, cnt_total, code);
		transSell(stock, price, cnt_total, stockBuy, code);
	}

	@Transactional
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
	synchronized void transSell(Stock stock, int price, int cnt_total, StockBuy stockBuy, Code code) {
		List<StockSell> list = stockSellsService.findTransactionList(stock, price);

		if (list.isEmpty()) return;
		for (StockSell stockSell : list) {
			if (cnt_total == 0) break;
			cnt_total -= transaction(stockBuy, stockSell);
		}
		stockDetailsService.updateStockDetail(stock, price);
	}

	/**
	 * 매도 거래 신청 메서드
	 *
	 * @param request
	 */
	@Transactional
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
	public void stockSell(StockTransactionRequest request) {
		String name = request.getName();
		int price = request.getPrice();
		int cnt_total = request.getCnt_total();
		User user = userService.userFindByEmail();
		Stock stock = stocksService.findByName(name);

		stockDetailsService.validStockPrice(stock, price);
		int cnt1 = stockHoldingsService.getStockHolding(user, stock);
		int cnt2 = stockSellsService.getStockSellWaitingList(user, stock, codeService.findByName("거래중"));

		if (cnt1 - cnt2 < cnt_total)
			throw BusinessException.of(ErrorCode.API_ERROR_STOCK_HOLDING_NOT_EXIST);

		Code code = codeService.findByName("거래중");
		StockSell stockSell = stockSellsService.save(user, stock, price, cnt_total, code);
		transBuy(stock, price, cnt_total, stockSell, code);

	}


	@Transactional
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
	synchronized void transBuy(Stock stock, int price, int cnt_total, StockSell stockSell, Code code) {
		List<StockBuy> list = stockBuysService.findTransactionList(stock, price);

		if (list.isEmpty()) return;
		for (StockBuy stockBuy : list) {
			if (cnt_total == 0) break;
			cnt_total -= transaction(stockBuy, stockSell);
		}
		stockDetailsService.updateStockDetail(stock, price);
	}


	/**
	 * 매도-매수 거래, 거래 이후 정보 수정 메서드
	 * 1. update ParkingDetail : 파킹통장 거래내역 추가
	 * 2. update ParkingBalance : 파킹통장 총 금액 수정
	 * 3. update StockSell/StockBuy : 거래ID 에 대해 cntNot 값 수정
	 * 4. update StockHodings : 매도인/매수인에 대해 보유 주식 수를 수정
	 *
	 * @param stockBuy  매수 거래
	 * @param stockSell 매도 거래
	 * @return 최종 거래 개수
	 */
	@Transactional
	public int transaction(StockBuy stockBuy, StockSell stockSell) {
		Code code = codeService.findByName("완료");

		int transCnt = Math.min(stockBuy.getCntNot(), stockSell.getCntNot());
		if (transCnt == 0) return 0;

		ParkingDetail detailSell = parkingDetailsService.saveStockSell(stockSell, parkingService.findByUser(stockSell.getUser()), transCnt, codeService.findByName("매도"));
		ParkingDetail detailBuy = parkingDetailsService.saveStockBuy(stockBuy, parkingService.findByUser(stockBuy.getUser()), transCnt, codeService.findByName("매수"));

		parkingService.updateParkingBalance(stockSell.getUser(), detailSell.getBalance());
		parkingService.updateParkingBalance(stockBuy.getUser(), detailBuy.getBalance());

		stockSellsService.stockSell(stockSell, transCnt, code);
		stockBuysService.stockBuy(stockBuy, transCnt, code);

		stockHoldingsService.stockSell(stockSell.getUser(), stockSell.getStock(), transCnt, stockSell.getPrice());
		stockHoldingsService.stockBuy(stockBuy.getUser(), stockBuy.getStock(), transCnt, stockBuy.getPrice());
		return transCnt;
	}


	/**
	 * 매도-매수 주문량 조회 메서드
	 *
	 * @param name 주식명
	 * @return 주가별 매도, 매수 주문 수량
	 */
	// 주가 실시간 조회
	public PriceAndStockOrderList getStockOrderInfo(String name) {
		// db에서 요청 받은 이름으로 주식 정보 조회
		Stock stock = stocksService.findByName(name);
		// 주식 id
		int id = stock.getId();
		// 현재 가격
		int price = stockDetailsService.getLastDetail(stock).getPrice();
		// 매도 / 매수 주문 리스트
		List<StockOrderList> stockOrderList = new ArrayList<>();
		// 주식 id에 해당하는 매도 주문 리스트 조회
		List<StockSell> sellList = stockSellsService.getSellOrderList(id, 0, LocalDate.now().atStartOfDay());
		if (!sellList.isEmpty()) {
			int sellPrice = sellList.get(0).getPrice();
			int sellCnt = 0;
			for (int i = 0; i < sellList.size(); i++) {
				if (sellPrice == sellList.get(i).getPrice()) {
					sellCnt += sellList.get(i).getCntNot();
				} else {
					stockOrderList.add(StockOrderList.builder().sellOrderCnt(sellCnt).price(sellPrice).build());
					sellCnt = sellList.get(i).getCntNot();
					sellPrice = sellList.get(i).getPrice();
				}
				if (i == sellList.size() - 1) {
					stockOrderList.add(StockOrderList.builder().sellOrderCnt(sellCnt).price(sellPrice).build());
				}
			}

		}
		// 주식 id에 해당하는 매수 주문 리스트 조회
		List<StockBuy> buyList = stockBuysService.getBuyOrderList(id, 0, LocalDate.now().atStartOfDay());
		if (!buyList.isEmpty()) {
			int buyPrice = buyList.get(0).getPrice();
			int buyCnt = 0;
			for (int i = 0; i < buyList.size(); i++) {
				if (buyPrice == buyList.get(i).getPrice()) {
					buyCnt += buyList.get(i).getCntNot();
				} else {
					stockOrderList.add(StockOrderList.builder().buyOrderCnt(buyCnt).price(buyPrice).build());
					buyCnt = buyList.get(i).getCntNot();
					buyPrice = buyList.get(i).getPrice();
				}
				if (i == buyList.size() - 1) {
					stockOrderList.add(StockOrderList.builder().buyOrderCnt(buyCnt).price(buyPrice).build());
				}
			}
		}
		stockOrderList.sort(Collections.reverseOrder());
		return PriceAndStockOrderList.builder().price(price).stockOrderList(stockOrderList).build();
	}

	// 주가 기간별 정보 조회(선그래프)
	public List<StockPriceHistoryByLine> getStockGraphInfosByLine(String name, Integer period) {
		// 주식
		Stock stock = stocksService.findByName(name);
		// 기간 pageable 처리 (몇개의 데이터 받을건지)
		Pageable pageable = PageRequest.ofSize(period);
		// 주식 id 값으로 리스트 조회
		List<StockDetail> stockDetailsList = stockDetailsService.findAllByStockOrderByCreatedAtDesc(stock, pageable);
		// response data로 넣기
		List<StockPriceHistoryByLine> result = new ArrayList<>();

		for (StockDetail sd : stockDetailsList) {
			StockPriceHistoryByLine history = StockPriceHistoryByLine.builder().price(sd.getPrice()).date(sd.getCreatedAt().toLocalDate()).build();
			result.add(history);
		}

		return result;
	}

	public List<StockPriceHistoryByBar> getStockGraphInfosByBar(String name, Integer period) {
		// 주식
		Stock stock = stocksService.findByName(name);
		// 전체 주식 기록 가져오기
		List<StockDetail> stockDetailsList = stockDetailsService.findAllByStockOrderByCreatedAtDesc(stock);
		// 요청 날짜 주기로 데이터 보내주기
		List<StockPriceHistoryByBar> result = new ArrayList<>();
		// 최고가
		int highestPrice = Integer.MIN_VALUE;
		// 최저가
		int lowestPrice = Integer.MAX_VALUE;
		// 시작가
		int startPrice = 0;
		// 등록일
		LocalDateTime createdAt = LocalDateTime.now();
		// 종가
		int price = 0;

		if (period == 1) {
			for (StockDetail sd : stockDetailsList) {
				long x = sd.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
				List<Integer> y = new ArrayList<>();
				y.add(sd.getStartPrice());
				y.add(sd.getHighestPrice());
				y.add(sd.getLowestPrice());
				y.add(sd.getPrice());
				StockPriceHistoryByBar priceHistoryByBar = StockPriceHistoryByBar.builder().x(x).y(y).build();
				result.add(priceHistoryByBar);
			}
		} else {
			for (int i = 0; i < stockDetailsList.size(); i++) {
				// 마지막 날 기준으로 최저가 최고가 초기화 및 날짜/종가 보내주기
				if (i % period == 0) {
					highestPrice = Integer.MIN_VALUE;
					lowestPrice = Integer.MAX_VALUE;
					createdAt = stockDetailsList.get(i).getCreatedAt();
					price = stockDetailsList.get(i).getPrice();
				}
				// 최고가 갱신
				if (highestPrice < stockDetailsList.get(i).getHighestPrice())
					highestPrice = stockDetailsList.get(i).getHighestPrice();
				// 최저가 갱신
				if (lowestPrice > stockDetailsList.get(i).getLowestPrice())
					lowestPrice = stockDetailsList.get(i).getLowestPrice();
				// 시작하는 날 기준으로 시작가 보내주기
				if (i % period == period - 1 || i == stockDetailsList.size() - 1) {
					List<Integer> y = new ArrayList<>();
					startPrice = stockDetailsList.get(i).getStartPrice();
					y.add(startPrice);
					y.add(highestPrice);
					y.add(lowestPrice);
					y.add(price);
					long x = createdAt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
					StockPriceHistoryByBar priceHistoryByBar = StockPriceHistoryByBar.builder()
							.x(x)
							.y(y).build();
					result.add(priceHistoryByBar);
				}
			}
		}
		return result;
	}

	public TotalStockListResponse getTotalStockList() {
		List<TotalStockList> totalStockList = new ArrayList<>();
		// 주식 이름
		List<Stock> totalStock = stocksService.findAllList();
		for (Stock stock : totalStock) {
			// 일별 주식 정보(오늘 기준)
			StockDetail stockDetail = stockDetailsService.getLastDetail(stock);
			// 주식명
			String name = stock.getName();
			// 현재가
			int price = stockDetail.getPrice();
			// 수익률 ((현재가 - 시가) / 시가) * 100
			int startPrice = stockDetail.getStartPrice();
			double incomeRatio = Math.round(((float) (price - startPrice) / startPrice) * 10000) / 100.0;
			// 오늘 거래량
			List<StockBuy> stockBuyList = stockBuysService.getTodayTransactions(stock, LocalDate.now().atStartOfDay());
			int transCnt = 0;
			for (StockBuy sb : stockBuyList) {
				transCnt += (sb.getCntTotal() - sb.getCntNot());
			}
			String imgUrl = stock.getImageUrl();

			TotalStockList stockInfo = TotalStockList.builder().name(name).price(price).incomeRatio(incomeRatio).transCnt(transCnt).imageUrl(imgUrl).build();

			totalStockList.add(stockInfo);
		}
		return TotalStockListResponse.builder().stock(totalStockList).build();
	}

	// 보유 주식 조회
	public MyStockListResponse getMyStockList() {
		List<MyStockList> myStockLists = new ArrayList<>();
		// 내 주식 전체 평가 손익
		int totalIncome = 0;
		// 내 전체 주식 평가
		int totalPrice = 0;
		User user = userService.userFindByEmail();
		List<StockHolding> stockHoldingList = stockHoldingsService.findAllByUser(user);
		for (StockHolding sh : stockHoldingList) {
			Stock stock = stocksService.findById(sh.getStock().getId());
			StockDetail stockDetail = stockDetailsService.getLastDetail(stock);
			// 주식 이름
			String name = stock.getName();
			// 주식 갯수
			int cnt = sh.getCnt();
			// 총 매수가
			int totalPriceAvg = sh.getTotal();
			// 현재가
			int priceCur = stockDetail.getPrice();
			// 현재 주식 평가총액
			int totalPriceCur = sh.getCnt() * priceCur;
			// 평균단가
			int priceAvg = totalPriceAvg / sh.getCnt();
			// 손익
			int income = totalPriceCur - totalPriceAvg;
			// 손익률
			double incomeRatio = Math.round(((float) income / totalPriceAvg) * 10000) / 100.0;
			totalIncome += income;
			totalPrice += totalPriceCur;
			// 이미지
			String imgUrl = stock.getImageUrl();
			myStockLists.add(MyStockList.builder().name(name).cnt(cnt).income(income).incomeRatio(incomeRatio).priceAvg(priceAvg).priceCur(priceCur).totalPriceAvg(totalPriceAvg).totalPriceCur(totalPriceCur).imageUrl(imgUrl).build());
		}
		return MyStockListResponse.builder().myStockList(myStockLists).totalIncome(totalIncome).totalPrice(totalPrice).build();
	}

	// 미체결 주식 조회
	public MyWaitingStockOrderResponse getMyWaitingStockOrder() {
		User user = userService.userFindByEmail();
		Code code = codeService.findById("S001");
		// 미체결 매수 리스트
		List<StockBuy> stockBuyList = stockBuysService.getWaitingStockOrders(user, code, LocalDate.now().atStartOfDay(), 0);
		// 미체결 매도 리스트
		List<StockSell> stockSellList = stockSellsService.getWaitingStockOrders(user, code, LocalDate.now().atStartOfDay(), 0);

		List<MyWaitingStockOrder> myWaitingStockOrders = new ArrayList<>();
		for (StockBuy sb : stockBuyList) {
			// 주식 이름
			String transName = stocksService.findById(sb.getStock().getId()).getName();
			// 1주당 주문 금액
			int price = sb.getPrice();
			// 미체결수
			int cnt = sb.getCntNot();
			// 총 주문금액
			int amount = price * cnt;
			System.out.println(transName);
			// 거래 종류(매도/매수)
			String type = "매수";
			myWaitingStockOrders.add(MyWaitingStockOrder.builder()
					.transName(transName)
					.amount(amount)
					.type(type)
					.cnt(cnt)
					.price(price)
					.build());
		}

		for (StockSell ss : stockSellList) {
			// 주식 이름
			String transName = stocksService.findById(ss.getStock().getId()).getName();
			// 1주당 주문 금액
			int price = ss.getPrice();
			// 미체결수
			int cnt = ss.getCntNot();
			// 총 주문금액
			int amount = price * cnt;
			// 거래 종류(매도/매수)
			String type = "매도";
			myWaitingStockOrders.add(MyWaitingStockOrder.builder()
					.transName(transName)
					.amount(amount)
					.type(type)
					.cnt(cnt)
					.price(price)
					.build());
		}
		return MyWaitingStockOrderResponse.builder().transaction(myWaitingStockOrders).build();
	}


	/**
	 * Ranking 갱신 메서드
	 */
	public void makeRankList() {
		HashMap<String, Integer> stockPriceList = stockDetailsService.getStockPriceList(stocksService.findAllList());
		List<Parking> parkingList = parkingService.findAllList();

		rankService.deleteRanking();
		for (Parking parking : parkingList) {
			List<StockHolding> stockHoldingList = stockHoldingsService.findAllByUser(parking.getUser());
			int balance = parking.getBalance();
			for (StockHolding stockHolding : stockHoldingList) {
				balance += stockHolding.getCnt() * stockPriceList.get(stockHolding.getStock().getName());
			}
			rankService.addUserScore(parking.getUser(), balance);
		}
	}

	/**
	 * 랭킹 1 ~ 10위 조회 메서드
	 *
	 * @return 랭킹 정보 리스트
	 */
	public RankingResponse getRanknigList() {
		List<RankingDetail> rankingList = getTop10UserRankings();
		return RankingResponse.of(rankingList);
	}

	/**
	 * 회원 랭킹 조회 메서드
	 * <p>
	 * 10위권 이하의 회원 : 동점자 처리X 순위 반영
	 * 10위권 이상의 회원 : 동점자 처리 순위 반영
	 *
	 * @return 랭킹 정보
	 */
	public RankingDetail getRanking() {
		User user = userService.userFindByEmail();
		int rank = Math.toIntExact(rankService.getUserRank(user));
		int balance = (int) rankService.getUserScore(user);

		List<RankingDetail> rankingList = getTop10UserRankings();
		for (RankingDetail detail : rankingList) {
			if (detail.getChildName().equals(user.getName()))
				return detail;
		}

		return RankingDetail.builder()
				.rank(rank)
				.balance(balance)
				.childName(user.getName())
				.build();
	}

	// 주식 상세 정보 조회
	public StockInfoResponse getStockInfo(StockNameRequest stockNameRequest) {
		String name = stockNameRequest.getName();
		Stock stock = stocksService.findByName(name);

		// 일별 주식 정보(오늘 기준)
		StockDetail stockDetail = stockDetailsService.getLastDetail(stock);
		// 현재가
		int price = stockDetail.getPrice();
		// 수익률 ((현재가 - 시가) / 시가) * 100
		int startPrice = stockDetail.getStartPrice();
		double incomeRatio = Math.round(((float) (price - startPrice) / startPrice) * 10000) / 100.0;
		// 오늘 거래량
		List<StockBuy> stockBuyList = stockBuysService.getTodayTransactions(stock, LocalDate.now().atStartOfDay());
		int transCnt = 0;
		for (StockBuy sb : stockBuyList) {
			transCnt += (sb.getCntTotal() - sb.getCntNot());
		}
		String imgUrl = stock.getImageUrl();

		return StockInfoResponse.builder()
				.price(price)
				.incomeRatio(incomeRatio)
				.transCnt(transCnt)
				.imageUrl(imgUrl)
				.upperLimitPrice(stockDetail.getUpperLimitPrice())
				.lowerLimitPrice(stockDetail.getLowerLimitPrice())
				.build();
	}

	public List<RankingDetail> getTop10UserRankings() {
		Set<ZSetOperations.TypedTuple<String>> top10UsersWithScores = rankService.getTop10Users();
		List<RankingDetail> userRankings = new ArrayList<>();
		int rank = 1;
		int prevRank = 1;
		int prevBalance = 0;
		for (ZSetOperations.TypedTuple<String> userWithScore : top10UsersWithScores) {
			if (prevBalance == 0)
				prevBalance = userWithScore.getScore().intValue();
			int balance = userWithScore.getScore().intValue();
			int curRank = (balance == prevBalance) ? prevRank : rank;
			RankingDetail userRanking = RankingDetail.builder()
					.childName(userService.findByUserUuid(UUID.fromString(userWithScore.getValue())).getName())
					.rank(curRank)
					.balance(balance)
					.build();
			userRankings.add(userRanking);
			prevBalance = balance;
			prevRank = curRank;
			rank++;
		}
		return userRankings;
	}
}
