package com.a502.backend.application.controller;

import com.a502.backend.application.entity.User;
import com.a502.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    //유저 등록
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Integer userId) {

        System.out.println("나를 불렀나");
        String email = userService.loginUser(userId);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("email", email);

        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

}
