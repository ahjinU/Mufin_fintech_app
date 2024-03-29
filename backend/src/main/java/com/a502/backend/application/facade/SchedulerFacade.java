package com.a502.backend.application.facade;

import com.a502.backend.application.entity.*;
import com.a502.backend.domain.account.AccountService;
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
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerFacade {
    private final WeatherService weatherService;
    private final StockDetailsService stockDetailsService;
    private final StocksService stocksService;
    private final StockFacade stockFacade;
    private final SavingsService savingsService;
    private final AccountService accountService;
    private final CodeService codeService;

//    @Scheduled(cron = "${schedule.cron.test}")
//    @Scheduled(cron = "${schedule.cron.start}")
    public void marketStart() {
        try {
            Weather weather = weatherService.weatherApi();
            stockDetailsService.setMarketStart(weather, stocksService.findAllList());
        } catch (Exception e) {
            log.info("* 비상이다 비상사태! 공습경보! Message: {}", e.getMessage());
        }
    }

//    @Scheduled(cron = "${schedule.cron.test}")
    public void calRank(){
        log.info("start cal Rank()");
        stockFacade.makeRankList();
    }

//    @Scheduled(cron = "${schedule.cron.test}")
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




}
