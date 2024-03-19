package com.a502.backend.application.facade;

import com.a502.backend.application.entity.User;
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
//        User user = userService.save("gkstmf1403@ssafy.com", "ssafy");
//        System.out.println(user.toString());
//        user.setName("한슬");
    }
}
