package com.a502.backend.domain.user;

import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User findById(int id){
        return userRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));
    }

    public String loginUser(int id) {
        User user = findById(id);
        return user.getEmail();
    }

    public User save(String email, String password){
        return userRepository.save(User.builder()
                .email(email)
                .password(password)
                .build());

    }

}
