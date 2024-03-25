package com.a502.backend.application.facade;

import com.a502.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class KeypadFacade {
    private final UserService userService;

}
