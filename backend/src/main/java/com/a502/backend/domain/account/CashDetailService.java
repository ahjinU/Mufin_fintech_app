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

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CashDetailService {
	private final CashDetailRepository cashDetailRepository;

	public List<CashDetail> getAllCashDetailsByUserAndPeriod(User user, LocalDateTime startDay, LocalDateTime endDay) {
		return cashDetailRepository.findByUserAndPeriod(user, startDay, endDay).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_CASHDETAIL_NOT_EXIST));
	}
}
