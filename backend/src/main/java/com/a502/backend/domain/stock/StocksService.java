package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StocksService {
    private final StocksRepository stocksRepository;

    public Stock findById(int id){
        return stocksRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_STOCK_NOT_EXIST));
    }

    public Stock findByName(String name){
        return stocksRepository.findByName(name).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_STOCK_NOT_EXIST));
    }

    public Stock save(String name, String imageUrl){
//        Stock stock = findByName(name);
//        if (stock != null){
//            stock.updateImageUrl(imageUrl);
//            return stock;
//        }
        return stocksRepository.save(Stock.builder()
                .name(name)
                .imageUrl(imageUrl)
                .build());
    }

    public List<Stock> findAllList(){
        return stocksRepository.findAll();
    }

}
