package com.a502.backend.domain.user;

import com.a502.backend.application.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public String loginUser(Integer userId) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findUserByUserId(userId));

        return userOptional.get().getEmail();
    }
}
