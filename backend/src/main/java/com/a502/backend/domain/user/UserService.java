package com.a502.backend.domain.user;

import com.a502.backend.application.config.dto.CustomUserDetails;
import com.a502.backend.application.config.dto.JWTokenDto;
import com.a502.backend.application.config.generator.JwtUtil;
import com.a502.backend.application.entity.Code;
import com.a502.backend.application.entity.TemporaryUser;
import com.a502.backend.application.entity.User;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.global.code.CodeRepository;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.domain.user.dto.LoginDto;
import com.a502.backend.domain.user.dto.SignUpDto;
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

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtil jwtUtil;
    private final TemporaryUserRepository temporaryUserRepository;
    private final CodeService codeService;
    private final ParkingService parkingService;


    @Transactional
    public UUID signup(String temporaryUserUuid, SignUpDto signUpDto, String parentName) {
        System.out.println("[UserService] 회원가입: " + temporaryUserUuid+"/"+signUpDto.toString());


        UUID uuid = convertToUuid(temporaryUserUuid);
        TemporaryUser temporaryUser = findTemporaryUser(uuid);

        checkDupleUser(temporaryUser);

        User parent = findUser(parentName);
        User user = convertToUserEntity(signUpDto, temporaryUser, parent);

        userRepository.save(user);
        temporaryUserRepository.delete(temporaryUser);

        //"아이"라면 파킹통장 생성 후 주식 초기화
        if(parent!=null)
            parkingService.createParkingAccount(user);

        return user.getUserUuid();

    }

    private TemporaryUser findTemporaryUser(UUID uuid) {

        TemporaryUser temporaryUser = temporaryUserRepository.findByTemporaryUserUuid(uuid)
                .orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_TEMPORARY_UUID_NOT_EXIST));

        return temporaryUser;
    }

    private void checkDupleUser(TemporaryUser temporaryUser) {
        findUserByTelephone(temporaryUser.getTelephone());
        findUserByEmail(temporaryUser.getEmail());
    }

    private User findUser(String parentName) {
        Optional<User> option =  userRepository.findByEmail(parentName);

        User parent = option.orElse(null);

        return parent;
    }

    public JWTokenDto login(LoginDto loginDto) {

        System.out.println("[UserService] 아이디/패스워드: "+loginDto.toString());
        System.out.println("[UserService] 1. authenticationToken 확인");

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        System.out.println("[UserService] 2. authenticationToken: "+authenticationToken);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        System.out.println("[UserService] 4. authentication:"+authentication.toString());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        JWTokenDto jwt = jwtUtil.generateToken(authentication);
        System.out.println("[Controller] 6. 액세스 토큰: "+jwt.getAccessToken());
        System.out.println(jwt.toString());

        return jwt;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      /*
        System.out.println("[service] email: "+username);
       */

        User findMember = userRepository.findByEmail(username)
                .orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));

        System.out.println("[UserService] findUser:"+findMember.toString());

        CustomUserDetails cU = new CustomUserDetails(findMember);
        System.out.println("[UserService] CustomUserDetails:"+cU.toString());
        return new CustomUserDetails(findMember);
    }


    public UUID checkDupleTelephone(String telephone) {
        System.out.println("[UserService] /checkDupleTelephone telephone:"+telephone);

        findUserByTelephone(telephone);

        TemporaryUser newUser = TemporaryUser.builder()
                .telephone(telephone)
                .build();

        System.out.println("[UserService] TemporaryUser:"+newUser.toString());

        TemporaryUser temporaryUser = temporaryUserRepository.save(newUser);

        return temporaryUser.getTemporaryUserUuid();
    }

	@Transactional
	public void checkDupleEmail(String temporaryUserUuidString, String email) {
		System.out.println("[UserService/이메일중복] 이메일: " + email);

        UUID uuid = convertToUuid(temporaryUserUuidString);
        System.out.println("[UserService] uuid: "+uuid.toString());

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

        User registUser =  User.builder()
                .name(signUpDto.getName()) // SignUpDto에 name 필드가 있다고 가정
                .email(temporaryUser.getEmail())
                .password(encodedPassword)
                .gender(signUpDto.getGender())
                .address(signUpDto.getAddress())
                .address2(signUpDto.getAddress2())
                .telephone(temporaryUser.getTelephone())
                .birth(LocalDate.parse(signUpDto.getBirth())) // ISO 날짜 포맷으로 변경
                .build();

        if(parent!=null){
            registUser.addParent(parent); // 찾은 부모 사용자를 설정
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

    public User userFindByEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Username 추출

        System.out.println("email: "+email);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));

    }

    public void save(String telephone, String email, String name, String password, String gender, String address, String address2, User parent) throws IOException {

        User registUser = User.builder()
                .telephone(telephone)
                .email(email)
                .name(name)
                .password(password)
                .gender(gender)
                .address(address)
                .address(address2)
                .birth(LocalDate.now())
                .build();

        if(parent!=null){
            registUser.addParent(parent); // 찾은 부모 사용자를 설정
        }
        userRepository.save(registUser);

    }


}
