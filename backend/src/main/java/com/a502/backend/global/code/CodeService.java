package com.a502.backend.global.code;

import com.a502.backend.application.entity.Code;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeService {
	private final CodeRepository codeRepository;

	public Code findById(String id) {
		return codeRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_NO_AUTHORIZATION));
	}

	public void save(String id, String name) {
			codeRepository.save(Code.builder().id(id).name(name).build());
	}
}
