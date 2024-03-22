package com.a502.backend.application.facade;

import com.a502.backend.application.entity.*;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.stock.*;
import com.a502.backend.domain.stock.response.PriceAndStockOrderList;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.domain.weather.Weather;
import com.a502.backend.domain.weather.WeatherService;
import com.a502.backend.global.common.RedisUtils;
import com.a502.backend.global.config.RedisTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerFacade {
    private final WeatherService weatherService;
    private final StockDetailsService stockDetailsService;
    private final StocksService stocksService;
    private final StockBuysService stockBuysService;
    private final StockSellsService stockSellsService;
    private final SimpMessageSendingOperations sendingOperations;
    private final StockHoldingsService stockHoldingsService;
    private final ParkingService parkingService;
    private final RedisUtils redisUtils;
    private final RankService rankService;
    private final UserService userService;
    private final RedisTemplate<String, Object> redisTemplate;

    @Scheduled(cron = "${schedule.cron.test}")
//    @Scheduled(cron = "${schedule.cron.start}")
    public void marketStart() {
        try {
            Weather weather = weatherService.weatherApi();
            stockDetailsService.setMarketStart(weather, stocksService.findAllList());
        } catch (Exception e) {
            log.info("* 비상이다 비상사태! 공습경보! Message: {}", e.getMessage());
        }
    }

    @Scheduled(cron = "${schedule.cron.test}")
//    @Scheduled(cron = "${schedule.cron.end}")
    public void marketEnd() {

    }

//    @Scheduled(cron = "${schedule.cron.test}")
//    @Scheduled(cron = "${schedule.cron.rank}")
    @Scheduled(cron = "${schedule.cron.test}")
    public void calRankList() {
        List<Stock> stockList = stocksService.findAllList();
        HashMap<String, Integer> stockPriceList = stockDetailsService.getStockPriceList(stockList);
        List<Parking> parkingList = parkingService.findAllList();
        HashMap<String, Integer> rankList = new HashMap<>();
        List<RankingDetail> rankingDetails = new ArrayList<>();

        int balanced = 10000;
        stockList.sort((o1, o2) -> (o1.getId() - o2.getId()));
        for (Parking parking : parkingList) {
            List<StockHolding> stockHoldingList = stockHoldingsService.getStockHolding(parking.getUser());
            stockHoldingList.sort((o1, o2) -> (o1.getStock().getId() - o2.getStock().getId()));
            int balance = parking.getBalance();

            for (StockHolding stockHolding : stockHoldingList) {
                balance += stockHolding.getCnt() * stockPriceList.get(stockHolding.getStock().getName());
            }

            redisTemplate.opsForZSet().add("ranking", "name"+balanced, balanced);
//            rankList.put(parking.getUser().getName(), balance);
//            rankingDetails.add(RankingDetail.builder()
//                    .childName(parking.getUser().getName())
//                    .balance(balance)
//                    .build());
        }
//        rankingDetails.sort((o1, o2) -> (o1.getBalance() - o2.getBalance()));
//        int rank = 1;
//        for (RankingDetail rankingDetail : rankingDetails) {
//            rankingDetail.setRank(rank++);
//        }
//
//        rankService.addRankingList(rankingDetails);
//        getRankList();
        redisTemplate.opsForZSet().add("ranking", "name"+balanced, balanced++);
    }
    public void getlist() {
        Set<Object> string =  redisTemplate.opsForZSet().reverseRange("ranking", 0, 9);
    }
    public void getRankList(){
        List<RankingDetail> ranks = rankService.getRankingList();
        sendingOperations.convertAndSend("/sub/ranks", ranks);
    }

    public void getRanking(int userId){
        User user = userService.findById(userId);
        RankingDetail rank = rankService.getRanking(user);
        sendingOperations.convertAndSend("/sub/ranks/my", rank);
    }

//    private final RedisTemplate<String, Object> redisTemplate;
    public void RedisTest(){


    }
}
