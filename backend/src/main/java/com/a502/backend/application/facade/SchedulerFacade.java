package com.a502.backend.application.facade;

import com.a502.backend.domain.stock.StockBuysService;
import com.a502.backend.domain.stock.StockDetailsService;
import com.a502.backend.domain.stock.StockSellsService;
import com.a502.backend.domain.stock.StocksService;
import com.a502.backend.domain.stock.response.PriceAndStockOrderList;
import com.a502.backend.domain.weather.Weather;
import com.a502.backend.domain.weather.WeatherService;
import com.a502.backend.global.config.RedisTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

    @Scheduled(cron = "${schedule.cron.test}")
//    @Scheduled(cron = "${schedule.cron.start}")
    public void marketStart() {
        try {
            Weather weather = weatherService.weatherApi();
            stockDetailsService.setMarketStart(weather,stocksService.findAllList());
        } catch (Exception e) {
            log.info("* 비상이다 비상사태! 공습경보! Message: {}", e.getMessage());
        }
    }

    @Scheduled(cron = "${schedule.cron.test}")
//    @Scheduled(cron = "${schedule.cron.end}")
    public void marketEnd() {

    }

//    @Scheduled(cron = "${schedule.cron.test}")
////    @Scheduled(cron = "${schedule.cron.rank}")
//    public void getRankList() {
//        sendingOperations.convertAndSend("/sub/orders/" + name, result);
//    }
}
