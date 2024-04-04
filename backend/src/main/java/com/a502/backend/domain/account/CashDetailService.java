package com.a502.backend.domain.account;

import com.a502.backend.application.entity.CashDetail;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.a502.backend.global.exception.ErrorCode.API_ERROR_CASHDETAIL_NOT_EXIST;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CashDetailService {
	private final CashDetailRepository cashDetailRepository;

	public List<CashDetail> getAllCashDetailsByUserAndPeriod(User user, LocalDateTime startDay, LocalDateTime endDay) {
		List<CashDetail> cashDetailList = cashDetailRepository.findAllByUserAndTransAtBetween(user, startDay, endDay);
		return cashDetailList;
	}

    public CashDetail findTransaction(UUID transactionUUID) {
		return  cashDetailRepository.findCashDetailByCashDetailUuid(transactionUUID).orElseThrow(() -> BusinessException.of(API_ERROR_CASHDETAIL_NOT_EXIST));
    }

    public CashDetail save(CashDetail cashDetail) {
		return cashDetailRepository.save(cashDetail);
    }
}
