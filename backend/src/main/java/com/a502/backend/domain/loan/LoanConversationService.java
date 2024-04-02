package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.LoanConversation;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanConversationService {
	private final LoanConversationRepository loanConversationRepository;

	public void saveLoanConversation(LoanConversation loanConversation) {
		loanConversationRepository.save(loanConversation);
	}

	public LoanConversation findByUserLast(User user) {
		return loanConversationRepository.findTopByUserOrderByCreatedAtDesc(user).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_LOAN_CONVERSATION_NOT_EXIST));
	}
}
