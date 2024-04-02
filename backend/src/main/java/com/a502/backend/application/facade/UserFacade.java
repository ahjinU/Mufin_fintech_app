package com.a502.backend.application.facade;

import com.a502.backend.application.config.dto.CustomUserDetails;
import com.a502.backend.application.config.dto.JWTokenDto;
import com.a502.backend.application.config.generator.JwtUtil;
import com.a502.backend.application.entity.*;
import com.a502.backend.domain.parking.ParkingDetailsService;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.stock.StockDetailsService;
import com.a502.backend.domain.stock.StockHoldingsService;
import com.a502.backend.domain.stock.StocksService;
import com.a502.backend.domain.user.TemporaryUserRepository;
import com.a502.backend.domain.user.UserRepository;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.domain.user.dto.LoginDto;
import com.a502.backend.domain.user.dto.SignUpDto;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserService userService;
    private final TemporaryUserRepository temporaryUserRepository;
    private final ParkingService parkingService;
    private final CodeService codeService;
    private final ParkingDetailsService parkingDetailsService;
    private final StocksService stocksService;
    private final StockDetailsService stockDetailsService;
    private final StockHoldingsService stockHoldingsService;



    @Transactional
    public User signup(String temporaryUserUuid, SignUpDto signUpDto, String parentName) throws IOException {
        System.out.println("[UserService] 회원가입: " + temporaryUserUuid + "/" + signUpDto.toString());


        UUID uuid = userService.convertToUuid(temporaryUserUuid);
        TemporaryUser temporaryUser = findTemporaryUser(uuid);

        userService.checkDupleUser(temporaryUser);

        User parent = userService.findUser(parentName);
        User user = userService.convertToUserEntity(signUpDto, temporaryUser, parent);

        userService.save(user);
        temporaryUserRepository.delete(temporaryUser);

        if (parent != null){
            Parking parkingAccount = parkingService.createParkingAccount(user);

            Code initCode = codeService.findTypeCode("시드머니");
            parkingDetailsService.initSeadMoney(parkingAccount, initCode);


            List<Stock> stocks = stocksService.findAllList();
            HashMap<String, Integer> stockStartPriceList = stockDetailsService.getStockStartPriceList(stocks);
            stockHoldingsService.initStockHolding(user,stocks,stockStartPriceList);
        }

        return user;
    }

    public JWTokenDto login(LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @Transactional
    public void checkDupleEmail(String temporaryUserUuidString, String email) {
        System.out.println("[UserService/이메일중복] 이메일: " + email);

        UUID uuid = userService.convertToUuid(temporaryUserUuidString);
        System.out.println("[UserService] uuid: " + uuid.toString());

        userService.findUserByEmail(email);

        TemporaryUser temporaryUser = temporaryUserRepository.findByTemporaryUserUuid(uuid)
                .orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_TEMPORARY_UUID_NOT_EXIST));

        temporaryUser.updateEmail(email);
    }


    @Transactional
    public UUID checkDupleTelephone(String telephone) {
        System.out.println("[UserService] /checkDupleTelephone telephone:" + telephone);

        userService.findUserByTelephone(telephone);

        TemporaryUser newUser = TemporaryUser.builder()
                .telephone(telephone)
                .build();

        System.out.println("[UserService] TemporaryUser:" + newUser.toString());

        TemporaryUser temporaryUser = temporaryUserRepository.save(newUser);

        return temporaryUser.getTemporaryUserUuid();
    }



    public UUID findUserByTelephone(String telephone) {
        TemporaryUser newUser = TemporaryUser.builder()
                .telephone(telephone)
                .build();

        System.out.println("[UserFacade] TemporaryUser:" + newUser.toString());

        TemporaryUser temporaryUser = temporaryUserRepository.save(newUser);

        return temporaryUser.getTemporaryUserUuid();
    }


    public TemporaryUser findTemporaryUser(UUID uuid) {

        TemporaryUser temporaryUser = temporaryUserRepository.findByTemporaryUserUuid(uuid)
                .orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_TEMPORARY_UUID_NOT_EXIST));

        return temporaryUser;
    }
}
