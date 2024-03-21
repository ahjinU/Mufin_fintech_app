package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Code;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
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

    }
}
