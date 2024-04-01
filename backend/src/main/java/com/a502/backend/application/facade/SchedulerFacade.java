package com.a502.backend.application.facade;

import com.a502.backend.application.entity.*;
import com.a502.backend.domain.account.AccountService;
import com.a502.backend.domain.loan.LoansService;
import com.a502.backend.domain.parking.ParkingDetailsService;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.savings.SavingsService;
import com.a502.backend.domain.stock.*;
import com.a502.backend.domain.stock.response.RankingResponse;
import com.a502.backend.domain.weather.Weather;
import com.a502.backend.domain.weather.WeatherService;
import com.a502.backend.global.code.CodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerFacade {
    private final WeatherService weatherService;
    private final StockDetailsService stockDetailsService;
    private final StocksService stocksService;
    private final StockFacade stockFacade;
    private final StockSellsService stockSellsService;
    private final StockBuysService stockBuysService;
    private final AccountService accountService;
    private final CodeService codeService;
    private final LoansService loansService;
    private final ParkingService parkingService;
    private final ParkingDetailsService parkingDetailsService;

    @Scheduled(cron = "${schedule.cron.start}")
    public void marketStart() {
        try {
            Weather weather = weatherService.weatherApi();
            stockDetailsService.setMarketStart(weather, stocksService.findAllList());
        } catch (Exception e) {
            log.info("* 비상이다 비상사태! 공습경보! Message: {}", e.getMessage());
        }
    }

    @Scheduled(cron = "${schedule.cron.rank}")
    public void calRank(){
        log.info("start cal Rank()");
        stockFacade.makeRankList();
    }

    @Scheduled(cron = "${schedule.cron.saving}")
    public void checkSavingMaturity(){
        Date now = new Date();
        Calendar cal = Calendar.getInstance();

        Code code = codeService.findByName("만기");
        List<Account> savings = accountService.findAllByASavingAccount();
        for(Account saving : savings){
            Date date = java.sql.Timestamp.valueOf(saving.getCreatedAt());
            cal.setTime(date);
            cal.add(Calendar.MONTH, saving.getSavings().getPeriod());
            Date date1 = cal.getTime();
            if (date1.before(now) || date1.equals(now)){
                accountService.updateStatusCode(saving, code);
            }
        }
    }


    @Scheduled(cron = "${schedule.cron.loan}")
    public void checkLoanArrears(){
        Date now = new Date();
        List<Loan> loans = loansService.findAllLoansInProgress();

        for (Loan loan : loans) {
            ZonedDateTime zonedDateTime = loan.getStartDate().atStartOfDay(ZoneId.systemDefault());
            Date date = Date.from(zonedDateTime.toInstant());
            long difference = now.getTime() - date.getTime();
            long diffDays = difference / (24 * 60 * 60 * 1000);
            diffDays /= loan.getPaymentDate();

            if (loan.getPaymentNowCnt() < diffDays){
                loansService.updateOverdueCnt(loan);
            }
        }

    }

    @Scheduled(cron = "${schedule.cron.interest}")
    public void checkParkingAccountInterest(){
        List<Parking> parkingList = parkingService.findAllList();

        for (Parking parking : parkingList) {
            int interest = (int) (parking.getBalance() * parking.getInterest() * 0.01);
            parkingService.updateParkingBalance(parking, interest + parking.getBalance());
            parkingDetailsService.saveInterest(parking, interest, codeService.findByName("이자"));
        }
    }

    @Scheduled(cron = "${schedule.cron.end}")
    public void marketEnd(){
        List<StockBuy> stockBuys = stockBuysService.getStockTransListOpend();
        List<StockSell> stockSells = stockSellsService.getStockTransListOpend();
        Code code = codeService.findByName("취소");

        for (StockBuy stockBuy: stockBuys) {
            stockBuysService.updateCode(stockBuy, code);
        }
        for (StockSell stockSell: stockSells) {
            stockSellsService.updateCode(stockSell, code);
        }

    }

}
