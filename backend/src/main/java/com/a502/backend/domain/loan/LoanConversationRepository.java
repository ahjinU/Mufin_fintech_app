package com.a502.backend.domain.loan;

import com.a502.backend.application.entity.LoanConversation;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface LoanConversationRepository extends JpaRepository<LoanConversation, Integer> {
	Optional<LoanConversation> findTopByUserOrderByCreatedAtDesc(User user);
}
