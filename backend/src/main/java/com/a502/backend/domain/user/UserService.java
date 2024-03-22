package com.a502.backend.domain.user;

import com.a502.backend.application.config.dto.CustomUserDetails;
import com.a502.backend.application.config.dto.JWTokenDto;
import com.a502.backend.application.config.generator.JwtUtil;
import com.a502.backend.application.entity.Code;
import com.a502.backend.domain.user.dto.LoginDto;
import com.a502.backend.domain.user.dto.SignUpDto;
import com.a502.backend.application.entity.TemporaryUser;
import com.a502.backend.application.entity.User;
import com.a502.backend.global.code.CodeRepository;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;
    private final TemporaryUserRepository temporaryUserRepository;
    private final CodeRepository codeRepository;

    @Transactional
    public UUID signup(String temporaryUserUuid, SignUpDto signUpDto, String parentName) {
        System.out.println("[UserService] 회원가입: " + signUpDto.toString());


        UUID uuid = convertToUuid(temporaryUserUuid);

        TemporaryUser temporaryUser = temporaryUserRepository.findByTemporaryUserUuid(uuid)
                .orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_TEMPORARY_UUID_NOT_EXIST));

        findUserByTelephone(temporaryUser.getTelephone());
        findUserByEmail(temporaryUser.getEmail());


        User parent = userRepository.findByEmail(parentName)
                .orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_TEMPORARY_UUID_NOT_EXIST));


        User user = convertToUserEntity(signUpDto, temporaryUser, parent);

        userRepository.save(user);

        temporaryUserRepository.delete(temporaryUser);

        return user.getUserUuid();

    }

    public JWTokenDto login(LoginDto loginDto) {

        /*
            System.out.println("[UserService] 아이디/패스워드: "+loginDto.toString());
            System.out.println("[UserService] 1. authenticationToken 확인");
            System.out.println("[UserService] 2. authenticationToken: "+authenticationToken);
            System.out.println("[UserService] 4. authentication:"+authentication.toString());
            System.out.println("[Controller] 5. 액세스 토큰 생성!");
            System.out.println("[Controller] 6. 액세스 토큰: "+jwt.getAccessToken());

         */
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        JWTokenDto jwt = jwtUtil.generateToken(authentication);

        return jwt;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      /*
        System.out.println("[service] email: "+username);
       */

        User findMember = userRepository.findByEmail(username)
                .orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));

        CustomUserDetails cU = new CustomUserDetails(findMember);
        System.out.println(cU.toString());
        return new CustomUserDetails(findMember);
    }


    public UUID checkDupleTelephone(String telephone) {

        findUserByTelephone(telephone);

        TemporaryUser newUser = TemporaryUser.builder()
                .telephone(telephone)
                .build();

        TemporaryUser temporaryUser = temporaryUserRepository.save(newUser);

        return temporaryUser.getTemporaryUserUuid();
    }

    @Transactional
    public void checkDupleEmail(String temporaryUserUuidString, String email) {

        UUID uuid = convertToUuid(temporaryUserUuidString);

        findUserByEmail(email);

        TemporaryUser temporaryUser = temporaryUserRepository.findByTemporaryUserUuid(uuid)
                .orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_TEMPORARY_UUID_NOT_EXIST));

        temporaryUser.updateEmail(email);
    }

    private UUID convertToUuid(String temporaryUserUuid) {
        UUID uuid = null;

        try {
            uuid = UUID.fromString(temporaryUserUuid);
        } catch (IllegalArgumentException e) {
            throw BusinessException.of(ErrorCode.API_ERROR_TEMPORARY_UUID_NOT_EXIST);
        }

        return uuid;
    }

    private User convertToUserEntity(SignUpDto signUpDto, TemporaryUser temporaryUser, User parent) {

        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        Code parentCode = codeRepository.findByName("부모");

        User registUser =  User.builder()
                .name(signUpDto.getName()) // SignUpDto에 name 필드가 있다고 가정
                .email(temporaryUser.getEmail())
                .password(encodedPassword)
                .gender(signUpDto.getGender())
                .address(signUpDto.getAddress())
                .address2(signUpDto.getAddress2())
                .telephone(temporaryUser.getTelephone())
                .typeCode(parentCode)
                .birth(LocalDate.parse(signUpDto.getBirth())) // ISO 날짜 포맷으로 변경
                .build();

        if(parent!=null){
            Code childCode = codeRepository.findByName("아이");
            registUser.addParent(parent,childCode); // 찾은 부모 사용자를 설정
        }

        return registUser;
    }

    private void findUserByTelephone(String telephone) {
        userRepository.findByTelephone(telephone).ifPresent(user -> {
            throw BusinessException.of(ErrorCode.API_ERROR_TELEPHONE_DUPLICATION_EXIST);
        });
    }

    private void findUserByEmail(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw BusinessException.of(ErrorCode.API_ERROR_EMAIL_DUPLICATION_EXIST);
        });
    }
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));
    }


}
