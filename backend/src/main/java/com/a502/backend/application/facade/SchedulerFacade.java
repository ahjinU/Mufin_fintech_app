package com.a502.backend.application.facade;

import com.a502.backend.application.entity.*;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.stock.*;
import com.a502.backend.domain.stock.response.RankingResponse;
import com.a502.backend.domain.weather.Weather;
import com.a502.backend.domain.weather.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchedulerFacade {
    private final WeatherService weatherService;
    private final StockDetailsService stockDetailsService;
    private final StocksService stocksService;
    private final StockFacade stockFacade;

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




}
