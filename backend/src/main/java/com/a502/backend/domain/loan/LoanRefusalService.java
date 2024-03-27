package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.Loan;
import com.a502.backend.application.entity.LoanRefusal;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanRefusalService {
	private final LoanRefusalRepository loanRefusalRepository;

	public LoanRefusal findByLoan(Loan loan) {
		return loanRefusalRepository.findByLoan(loan);
	}


}
