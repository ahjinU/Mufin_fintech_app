package com.a502.backend.application.facade;

import com.a502.backend.domain.numberimg.NumberImageService;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.account.AccountService;
import com.a502.backend.domain.stock.StockDetailsService;
import com.a502.backend.domain.stock.StocksService;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.common.S3FileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

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
    private final AccountService accountService;
    private  final ParkingService parkngService;

    private final SchedulerFacade schedulerFacade;
    public void run() throws IOException {

//        List<Stock> stocks = stocksService.findAllList();
//
//        for(Stock stock : stocks){
//            stockDetailsService.save(10000, 10000, 10000, 20000, 10000, 10000, stock);
//        }

//        initCode();
//        initStock();
//        initKeypadImage();

    }

    /**
     * 상태 코드를 초기화 메서드
     */
    private void initCode() {
        codeService.save("PD001","이자");
        codeService.save("PD002","매도");
        codeService.save("PD003","매수");
        codeService.save("PD004","시드머니");
        codeService.save("S001","거래중");
        codeService.save("S002","완료");
        codeService.save("S003","취소");
        codeService.save("AS001","만기");
        codeService.save("AS002","정상");
        codeService.save("AS003","해지");
        codeService.save("AS004","정지");
        codeService.save("AS001","만기");
        codeService.save("AT001","입출금");
        codeService.save("AT002","적금계좌");
        codeService.save("CD001","지출");
        codeService.save("CD002","수입");
        codeService.save("ADT001","대출");
        codeService.save("ADT002","적금이체");
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
    }

    /**
     * 주식 종목을 초기화 메서드
     * @throws IOException
     */
    private void initStock() throws IOException {
        String image = s3FileUploader.uploadFile("images/" + "stock/"+ "vane" + ".png");
        stocksService.save("바람개비", image);
        image = s3FileUploader.uploadFile("images/" + "stock/"+ "umbrella" + ".png");
        stocksService.save("우산", image);
        image = s3FileUploader.uploadFile("images/" + "stock/"+ "icecream" + ".png");
        stocksService.save("아이스크림", image);
        image = s3FileUploader.uploadFile("images/" + "stock/"+ "duck" + ".png");
        stocksService.save("눈오리", image);
    }

    /**
     * 비밀번호 키패드 이미지 초기화 메서드
     * @throws IOException
     */
    private void initKeypadImage() throws IOException{
        for (int i = 0; i < 10; i++) {
            String image = s3FileUploader.uploadFile("images/"+ "icon-" + i + ".png");
            numberImageService.saveImage(image);
        }
    }

    /**
     * 주식 초기화 메서드
     * @throws IOException
     */
    private void initStocks() throws IOException {
        stocksService.save("아이스크림 회사", "이미지1");
        stocksService.save("우산 회사", "이미지2");
        stocksService.save("눈오리 회사", "이미지3");
        stocksService.save("썬글라스 회사", "이미지4");
        stocksService.save("마스크 회사", "이미지5");
        stocksService.save("바람막이 회사", "이미지6");
    }

    private void initUser() throws IOException {

        userService.save("010-1111-1111","ssafy","은평","1234","여","몰루1","몰루2",null);
        userService.save("010-2222-2222","mom","은평","1234","여","몰루1","몰루2",null);
        userService.save("010-3333-3333","dad","한슬","1234","여","몰루1","몰루2",null);
        userService.save("010-4444-4444","child1","지나","1234","여","몰루1","몰루2", userService.findById(1));
        userService.save("010-4444-4444","child2","수민","1234","여","몰루1","몰루2", userService.findById(2));
    }

    private void initAccount() throws IOException {
        accountService.createInit(1,"1234");
        accountService.createInit(2,"1234");
    }

    private void initParking() throws IOException {
        parkngService.createParkingAccount(userService.findById(1));
        parkngService.createParkingAccount(userService.findById(2));

    }

}
