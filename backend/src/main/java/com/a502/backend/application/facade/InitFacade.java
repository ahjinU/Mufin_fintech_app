package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Code;
import com.a502.backend.application.entity.Stock;
import com.a502.backend.application.entity.StockDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.numberimg.NumberImageService;
import com.a502.backend.domain.stock.StockDetailsService;
import com.a502.backend.domain.stock.StocksService;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.common.S3FileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class InitFacade {

    private final UserService userService;
    private final CodeService codeService;
    private final StocksService stocksService;
    private final StockDetailsService stockDetailsService;
    private final S3FileUploader s3FileUploader;
    private final NumberImageService numberImageService;
    public void run() throws IOException {
//        User user = userService.save("gkstmf1403@ssafy.com", "ssafy");
//        System.out.println(user.toString());
//        user.setName("한슬");

        codeService.save("PD001","이자");
        codeService.save("PD002","매도");
        codeService.save("PD003","매수");
        codeService.save("S001","거래중");
        codeService.save("S002","완료");
        codeService.save("S003","취소");
        codeService.save("AS001","만기");
        codeService.save("AS002","정상");
        codeService.save("AS003","해지");
        codeService.save("AS004","정지");
        codeService.save("AS001","만기");
        codeService.save("AT001","입출금");
        codeService.save("AT002","적금");
        codeService.save("CD001","지출");
        codeService.save("CD002","수입");
        codeService.save("ADT001","대출");
        codeService.save("ADT002","적금");
        codeService.save("ADT003","결제");
        codeService.save("ADT004","이체");
        codeService.save("ADT005","용돈");
        codeService.save("ADS001","거래완료");
        codeService.save("ADS002","거래취소");
        codeService.save("U001","부모");
        codeService.save("U002","아이");
        codeService.save("AL001","적금");
        codeService.save("AL002","적금만기");
        codeService.save("AL003","대출요청");
        codeService.save("L001","심사중");
        codeService.save("L002","진행중");
        codeService.save("L003","거절");
        codeService.save("L004","상환완료");



//        List<Stock> stocks = stocksService.findAllList();
//
//        for(Stock stock : stocks){
//            stockDetailsService.save(10000, 10000, 10000, 20000, 10000, 10000, stock);
//        }

//        initStock();
//        saveKeypadImage();
    }

    /**
     * 주식 종목을 초기화 메서드
     * @throws IOException
     */
    private void initStock() throws IOException {
        String image = s3FileUploader.uploadFile("images/" + "stock/"+ "wind" + ".png");
        stocksService.save("바람개비", image);
        image = s3FileUploader.uploadFile("images/" + "stock/"+ "rain" + ".png");
        stocksService.save("우비", image);
        image = s3FileUploader.uploadFile("images/" + "stock/"+ "icecream" + ".png");
        stocksService.save("아이스크림", image);
        image = s3FileUploader.uploadFile("images/" + "stock/"+ "snow" + ".png");
        stocksService.save("눈오리", image);

    }

    /**
     * 비밀번호 키패드 이미지 초기화 메서드
     * @throws IOException
     */
    private void initKeypadImage() throws IOException{
//        for (int i = 0; i < 10; i++) {
//            String image = s3FileUploader.uploadFile("images/"+ "keypad" + i + ".png");
//            numberImageService.saveImage(image);
//        }
        String image = s3FileUploader.uploadFile("images/" + "shinchan"+ ".png");
        numberImageService.saveImage(image);
    }
}
