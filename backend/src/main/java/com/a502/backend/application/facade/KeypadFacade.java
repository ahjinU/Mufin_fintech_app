package com.a502.backend.application.facade;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.account.request.AccounKeypadCreateRequest;
import com.a502.backend.domain.account.request.AccountPasswordRequest;
import com.a502.backend.domain.account.request.AccountValidPasswordRequest;
import com.a502.backend.domain.account.response.AccountValidPasswordResponse;
import com.a502.backend.domain.numberimg.NumberImageService;
import com.a502.backend.domain.numberimg.request.KeyPadRequest;
import com.a502.backend.domain.numberimg.request.ValidPasswordRequest;
import com.a502.backend.domain.numberimg.response.KeypadListResponse;
import com.a502.backend.domain.account.AccountService;
import com.a502.backend.domain.user.UserService;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class KeypadFacade {
    private final UserService userService;
    private final AccountService accountService;
    private final NumberImageService numberImageService;
    private final CodeService codeService;
    private final PasswordEncoder passwordEncoder;


    public KeypadListResponse getKeypadList(){
        User user = userService.userFindByEmail();
        accountService.validCheckAccountIsCreated(user);
        return new KeypadListResponse(numberImageService.getKeypadList(user.getUserUuid().toString()));
    }

    public KeypadListResponse getKeypadList(AccounKeypadCreateRequest request){
        User user = userService.userFindByEmail();
        Account account = accountService.findByAccountNumber(request.getAccountNumberOut());
        if (!user.getEmail().equals(account.getUser().getEmail()))
            throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_IS_NOT_YOURS);
        if (account.getStatusCode().getName().equals("정지"))
            throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_IS_STOPPED);
        return new KeypadListResponse(numberImageService.getKeypadList(user.getUserUuid().toString()));
    }

    public void craeteAccount(AccountPasswordRequest request){
        User user = userService.userFindByEmail();
        String password = numberImageService.decodePassword(user.getUserUuid().toString(), request.getPassword());
        log.info("PASSWORD : {}", password);
        accountService.createDepositWithdrawalAccount(password);
        numberImageService.deleteNumberList(user.getUserUuid().toString());
    }

    public AccountValidPasswordResponse validAccountPassword(AccountValidPasswordRequest request){
        User user = userService.userFindByEmail();
        Account account = accountService.findByAccountNumber(request.getAccountNumberOut());
        if (!user.getEmail().equals(account.getUser().getEmail()))
            throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_IS_NOT_YOURS);
        int cnt = 0;
        if (checkAccountPassword(account, request.getPassword())) {
            account.updateIncorectCnt(0);
            numberImageService.deleteNumberList(user.getUserUuid().toString());
        } else {
            cnt = account.updateIncorectCnt(account.getIncorrectCount() + 1);
            if (cnt == 5){
                account.updateCode(codeService.findByName("정지"));
                numberImageService.deleteNumberList(user.getUserUuid().toString());
                throw BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_IS_STOPPED);
            }
        }
        return new AccountValidPasswordResponse(cnt);
    }

    public boolean checkAccountPassword(Account account, List<Integer> password){
        String encodedPassword = account.getPassword();
        String rawPassword = numberImageService.decodePassword(account.getUser().getUserUuid().toString(), password);
        log.info("rawPW :  {}", rawPassword);
        log.info("encodePW :  {}", encodedPassword);
        if (passwordEncoder.matches(rawPassword, encodedPassword))
            return true;
        else return false;
    }

}
