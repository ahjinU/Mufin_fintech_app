package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.Loan;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
//@Transactional(readOnly = true)
@Service
public class LoansService {
	private final LoansRepository loansRepository;

	public Loan findById(int id) {
		return loansRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_LOAN_NOT_EXIST));
	}

//	public List<Loan> findByChild(User child) {
//        return loansRepository.getLoanChild(child).orElseThrow(()->BusinessException.of(ErrorCode.API_ERROR_LOAN_NOT_EXIST));
//	}

}
