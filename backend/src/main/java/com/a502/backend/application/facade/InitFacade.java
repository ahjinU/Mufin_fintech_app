package com.a502.backend.application.facade;

import com.a502.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional
@RequiredArgsConstructor
@Service
public class InitFacade {

    private final UserService userService;
    public void run() throws IOException {
        userService.save("gkstmf1403@naver.com", "ssafy");
    }
}
