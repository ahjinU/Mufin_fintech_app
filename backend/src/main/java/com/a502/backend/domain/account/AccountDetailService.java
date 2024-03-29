package com.a502.backend.domain.account;

import com.a502.backend.application.entity.Account;
import com.a502.backend.application.entity.AccountDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountDetailService {
	private final AccountDetailRepository accountDetailRepository;

	// 거래 내역 등록
	public AccountDetail save(AccountDetail accountDetail) {
		return accountDetailRepository.save(accountDetail);
	}

    public List<AccountDetail> findAccountDetailsForUserAndPeriod(User holderUser, LocalDateTime startDate, LocalDateTime endDate) {

		return accountDetailRepository.findAllByAccountUserAndCreatedAtBetween(holderUser, startDate, endDate);
	}


    public AccountDetail findTransaction(UUID transactionUUID) {
		return accountDetailRepository.findAccountDetailByAccountDetailUuid(transactionUUID).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_ACCOUNT_DETAIL_NOT_EXIST));
    }
}
