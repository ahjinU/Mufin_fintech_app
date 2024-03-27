package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.LoanDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanDetailService {
	private final LoanDetailRepository loanDetailRepository;

	public void save(LoanDetail loanDetail) {
		loanDetailRepository.save(loanDetail);
	}
}
