package com.a502.backend.domain.allowance;

import com.a502.backend.application.entity.Memo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    public void save(Memo memo) {
        memoRepository.save(memo);
    }
}
