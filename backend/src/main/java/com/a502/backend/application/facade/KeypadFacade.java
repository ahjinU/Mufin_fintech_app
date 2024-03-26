package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.numberimg.NumberImageService;
import com.a502.backend.domain.numberimg.request.CreatePasswordRequest;
import com.a502.backend.domain.numberimg.request.KeyPadRequest;
import com.a502.backend.domain.numberimg.request.ValidPasswordRequest;
import com.a502.backend.domain.numberimg.response.KeypadListResponse;
import com.a502.backend.domain.payment.AccountService;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class KeypadFacade {
    private final UserService userService;
    private final AccountService accountService;
    private final NumberImageService numberImageService;
    private final CodeService codeService;


    public KeypadListResponse getKeypadList(int userId, KeyPadRequest request){
        User user = userService.findById(userId);
        Account account = accountService.findByAccountNumber(request.getAccountNumberOut());
        if (!user.getEmail().equals(account.getUser().getEmail()))
            throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_IS_NOT_YOURS);
        return new KeypadListResponse(numberImageService.getKeypadList(account.getAccountNumber()));
    }

    public void craeteAccountPassword(int userId, CreatePasswordRequest request){
        User user = userService.findById(userId);
        Account account = accountService.findByAccountNumber(request.getAccountNumberOut());
        if (!user.getEmail().equals(account.getUser().getEmail()))
            throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_IS_NOT_YOURS);

        String password = numberImageService.decodePassword(account.getAccountNumber(), request.getPassword());
        account.updatePassword(password);
//        saveAccount
    }

    public int validAccountPassword(int userId, ValidPasswordRequest request){
        User user = userService.findById(userId);
        Account account = accountService.findByAccountNumber(request.getAccountNumberOut());

        if (!user.getEmail().equals(account.getUser().getEmail()))
            throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_IS_NOT_YOURS);
        if (checkAccountPassword(account, request.getPassword())){
            account.updateIncorectCnt(0);
        }else {
            int cnt = account.updateIncorectCnt(account.getIncorrectCount() + 1);
            if (cnt == 5){
                account.updateCode(codeService.findByName("정지"));
            }
        }
        return account.getIncorrectCount();
    }

    public boolean checkAccountPassword(Account account, List<Integer> password){
        if (account.getPassword().equals(numberImageService.decodePassword(account.getAccountNumber(), password)))
            return true;
        else return false;
    }

}
