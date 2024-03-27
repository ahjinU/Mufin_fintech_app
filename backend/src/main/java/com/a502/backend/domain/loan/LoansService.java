package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.Loan;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
//@Transactional(readOnly = true)
@Service
public class LoansService {
	private final LoansRepository loansRepository;

	public Loan findById(int id) {
		return loansRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_LOAN_NOT_EXIST));
	}

	public void saveLoan(Loan loan) {
		loansRepository.save(loan);
	}

	public List<Loan> getAllLoansForChild(User child) {
		List<Loan> loans = loansRepository.findAllLoansInProgressByUser(child);
		if (loans.isEmpty()) {
			throw BusinessException.of(ErrorCode.API_ERROR_LOAN_NOT_EXIST);
		}
		return loans;
	}

	public Loan findByUuid(String loanUuid){
		UUID Uuid = UUID.fromString(loanUuid);
		return loansRepository.findByUuid(Uuid).orElseThrow(()->BusinessException.of(ErrorCode.API_ERROR_LOAN_NOT_EXIST));
	}
}
