package com.a502.backend.domain.user;

import com.a502.backend.application.dto.LoginDto;
import com.a502.backend.application.dto.UserDto;
import com.a502.backend.application.entity.Authority;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import io.lettuce.core.ScriptOutputType;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    public User findById(int id){
        return userRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));
    }

    public String loginUser(int id) {
        User user = findById(id);
        return user.getEmail();
    }



    @Transactional
    public UUID signup(User user) {

        return user.getUserUuid();
    }

    public User login(LoginDto loginUser) {

        LoginDto regUser = new LoginDto("TEST","TEST");
        User registUser = modelMapper.map(regUser,User.class);
        Object ob = userRepository.save(registUser);

        System.out.println(ob);
        System.out.println("=====등록 테스트=====");

        System.out.println("[service] email: "+loginUser.getEmail());

        User findMember = userRepository.findByEmail(loginUser.getEmail())
                .orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));

        if (!findMember.checkPassword(loginUser.getPassword())) {
            throw BusinessException.of(ErrorCode.API_ERROR_USER_INCORRECT_PASSWORD);
        }

        return findMember;

    }
}
