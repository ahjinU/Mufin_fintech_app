package com.a502.backend.domain.savings;

import com.a502.backend.application.entity.Savings;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavingsService {
    private final SavingsRepository savingsRepository;

    public Savings findById(int id){
        return savingsRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_SAVING_NOT_EXIST));
    }
}
