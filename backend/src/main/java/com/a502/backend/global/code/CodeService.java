package com.a502.backend.global.code;

import com.a502.backend.application.entity.Code;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodeService {
	CodeRepository codeRepository;

	public Code findById(String id){
		return codeRepository.findById(id).orElseThrow(()->BusinessException.of(ErrorCode.API_ERROR_NO_AUTHORIZATION));
	}
}
