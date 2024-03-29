package com.a502.backend.domain.stock;

import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockDetail;
import com.a502.backend.domain.weather.Weather;
import com.a502.backend.global.code.StockCode;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StockDetailsService {
	private final StockDetailsRepository stockDetailsRepository;

	@Transactional
	public StockDetail getLastDetail(Stock stock) {
		return stockDetailsRepository.findTopByStockOrderByCreatedAtDesc(stock);
	}

	@Transactional
	public void validStockPrice(Stock stock, int price) {
		StockDetail stockDetail = getLastDetail(stock);
		if (stockDetail.getLowerLimitPrice() > price
				|| stockDetail.getUpperLimitPrice() < price)
			throw BusinessException.of(ErrorCode.API_ERROR_STOCK_PRICE_OUT_OF_RANGE);
	}

    @Transactional
    public void updateStockDetail(Stock stock, int price){
        StockDetail stockDetail = getLastDetail(stock);
        stockDetail.setPrice(price);
        stockDetail.setHighestPrice(Math.max(stockDetail.getHighestPrice(), price));
        stockDetail.setLowestPrice(Math.min(stockDetail.getLowestPrice(), price));
    }

    @Transactional
    public HashMap<String, Integer> getStockPriceList(List<Stock> stocks){
        HashMap<String, Integer> hashList = new HashMap<>();

        for(Stock stock: stocks){
            StockDetail detail = getLastDetail(stock);
            hashList.put(stock.getName(), detail.getPrice());
        }
        return hashList;
    }

    public StockDetail save(int price, int highestPrice, int lowestPrice, int upperLimitPrice, int lowerLimitPrice, int startPrice, Stock stock) {
        return stockDetailsRepository.save(StockDetail.builder()
                .price(price)
                .highestPrice(highestPrice)
                .lowestPrice(lowestPrice)
                .lowerLimitPrice(lowerLimitPrice)
                .upperLimitPrice(upperLimitPrice)
                .startPrice(startPrice)
                .stock(stock)
                .build());
    }

    public StockDetail saveInit(int startPrice, int lowerLimitPrice, int upperLimitPrice, Stock stock){
        return stockDetailsRepository.save(StockDetail.builder()
                .price(startPrice)
                .highestPrice(startPrice)
                .lowestPrice(startPrice)
                .lowerLimitPrice(lowerLimitPrice)
                .upperLimitPrice(upperLimitPrice)
                .startPrice(startPrice)
                .stock(stock)
                .build());
    }

    public void setMarketStart(Weather weather, List<Stock> stocks){
        double[] ratioDif = calRatioDif(weather);
        int i = 0;

        for(Stock stock : stocks){
            StockDetail stockDetail = getLastDetail(stock);
            int price = (stockDetail == null) ? 10000 : stockDetail.getPrice();
            int startPrice = (int) (price * ratioDif[i++]);

            saveInit(startPrice,startPrice - 1000 , startPrice + 1000, stock);
        }
    }

    public double[] calRatioDif(Weather weather){
        double[] result = {1.0, 1.0, 1.0, 1.0};

        switch(weather.getHumidityLevel()){
            case 1:
                result[1] -= StockCode.STOCK_PRICE_LV2;
                result[2] += StockCode.STOCK_PRICE_LV3;
                break;
            case 2:
                result[1] -= StockCode.STOCK_PRICE_LV1;
                result[2] += StockCode.STOCK_PRICE_LV2;
                break;
            case 3:
                result[1] += StockCode.STOCK_PRICE_LV3;
                result[2] -= StockCode.STOCK_PRICE_LV1;
                break;
        }

        switch(weather.getWindLevel()){
            case 1:
                result[0] -= StockCode.STOCK_PRICE_LV1;
                result[1] -= StockCode.STOCK_PRICE_LV1;
                break;
            case 2:
                result[0] += StockCode.STOCK_PRICE_LV2;
                break;
            case 3:
                result[0] += StockCode.STOCK_PRICE_LV3;
                result[1] += StockCode.STOCK_PRICE_LV3;
                break;
        }

        switch(weather.getRainLevel()){
            case 0:
                result[0] += StockCode.STOCK_PRICE_LV1;
                result[1] -= StockCode.STOCK_PRICE_LV1;
                result[2] += StockCode.STOCK_PRICE_LV1;
                result[3] -= StockCode.STOCK_PRICE_LV1;
                break;
            case 1:
                result[1] += StockCode.STOCK_PRICE_LV1;
                result[3] -= StockCode.STOCK_PRICE_LV3;
                break;
            case 2:
                result[1] += StockCode.STOCK_PRICE_LV1;
                result[3] -= StockCode.STOCK_PRICE_LV1;
                break;
            case 3:
                result[1] += StockCode.STOCK_PRICE_LV3;
                result[3] -= StockCode.STOCK_PRICE_LV2;
                break;
        }

        switch(weather.getCloudLevel()){
            case 1:
                result[1] -= StockCode.STOCK_PRICE_LV1;
                result[2] += StockCode.STOCK_PRICE_LV3;
                result[3] -= StockCode.STOCK_PRICE_LV3;
                break;
            case 2:
                result[1] += StockCode.STOCK_PRICE_LV2;
                result[2] += StockCode.STOCK_PRICE_LV2;
                result[3] += StockCode.STOCK_PRICE_LV1;
                break;
            case 3:
                result[1] += StockCode.STOCK_PRICE_LV3;
                result[2] -= StockCode.STOCK_PRICE_LV1;
                result[3] += StockCode.STOCK_PRICE_LV3;
                break;
        }
        switch(weather.getSnowLevel()){
            case 0:
                result[0] += StockCode.STOCK_PRICE_LV1;
                result[1] -= StockCode.STOCK_PRICE_LV1;
                result[2] += StockCode.STOCK_PRICE_LV1;
                result[3] -= StockCode.STOCK_PRICE_LV1;
                break;
            case 1:
                result[0] += StockCode.STOCK_PRICE_LV1;
                result[3] += StockCode.STOCK_PRICE_LV1;
                break;
            case 2:
                result[3] += StockCode.STOCK_PRICE_LV2;
                break;
            case 3:
                result[3] += StockCode.STOCK_PRICE_LV3;
                break;
        }

        return result;
    }

	public List<StockDetail> findAllByStockOrderByCreatedAtDesc(Stock stock, Pageable pageable) {
		return stockDetailsRepository.findAllByStockOrderByCreatedAtDesc(stock, pageable);
	}

	public List<StockDetail> findAllByStockOrderByCreatedAtDesc(Stock stock){
		return stockDetailsRepository.findAllByStockOrderByCreatedAtDesc(stock);
	}
}
