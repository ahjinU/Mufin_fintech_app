package com.a502.backend.domain.allowance;

import com.a502.backend.application.entity.Memo;
import com.a502.backend.application.entity.ReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<Memo,Integer> {
}
