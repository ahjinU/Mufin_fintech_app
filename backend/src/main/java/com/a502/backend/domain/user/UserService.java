package com.a502.backend.domain.user;

import com.a502.backend.application.config.dto.CustomUserDetails;
import com.a502.backend.application.config.dto.JWTokenDto;
import com.a502.backend.application.config.generator.JwtUtil;
import com.a502.backend.application.entity.TemporaryUser;
import com.a502.backend.application.entity.User;
import com.a502.backend.application.facade.ParkingFacade;
import com.a502.backend.application.facade.UserFacade;
import com.a502.backend.domain.parking.ParkingService;
import com.a502.backend.domain.user.dto.LoginDto;
import com.a502.backend.domain.user.dto.SignUpDto;
import com.a502.backend.global.code.CodeService;
import com.a502.backend.global.error.BusinessException;
import com.a502.backend.global.exception.ErrorCode;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final JwtUtil jwtUtil;

	public JWTokenDto login(LoginDto loginDto) {

		System.out.println("[UserService] 아이디/패스워드: " + loginDto.toString());
		System.out.println("[UserService] 1. authenticationToken 확인");

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

		System.out.println("[UserService] 2. authenticationToken: " + authenticationToken);

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		System.out.println("[UserService] 4. authentication:" + authentication.toString());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		JWTokenDto jwt = jwtUtil.generateToken(authentication);
		System.out.println("[Controller] 6. 액세스 토큰: " + jwt.getAccessToken());
		System.out.println(jwt.toString());

		return jwt;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User findMember = userRepository.findByEmail(username)
				.orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));

		System.out.println("[UserService] findUser:" + findMember.toString());

		CustomUserDetails cU = new CustomUserDetails(findMember);
		System.out.println("[UserService] CustomUserDetails:" + cU.toString());
		return new CustomUserDetails(findMember);
	}

	public void checkDupleUser(TemporaryUser temporaryUser) {
		findUserByTelephone(temporaryUser.getTelephone());
		findUserByEmail(temporaryUser.getEmail());
	}
	public UUID convertToUuid(String temporaryUserUuid) {
		UUID uuid = null;

		try {
			uuid = UUID.fromString(temporaryUserUuid);
		} catch (IllegalArgumentException e) {
			throw BusinessException.of(ErrorCode.API_ERROR_TEMPORARY_UUID_NOT_EXIST);
		}

		return uuid;
	}


	public User findUser(String parentName) {
		Optional<User> option = userRepository.findByEmail(parentName);

		User parent = option.orElse(null);

		return parent;
	}


	public void findUserByTelephone(String telephone) {
		userRepository.findByTelephone(telephone).ifPresent(user -> {
			throw BusinessException.of(ErrorCode.API_ERROR_TELEPHONE_DUPLICATION_EXIST);
		});
	}

	public void findUserByEmail(String email) {
		userRepository.findByEmail(email).ifPresent(user -> {
			throw BusinessException.of(ErrorCode.API_ERROR_EMAIL_DUPLICATION_EXIST);
		});
	}

	public User findById(int id) {
		return userRepository.findById(id).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));
	}

	public User userFindByEmail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName(); // Username 추출

		System.out.println("email: " + email);

		return userRepository.findByEmail(email)
				.orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));

	}

	public void save(User user) throws IOException {
		userRepository.save(user);
    }

	public User findByUserUuid(String userUuid) {
		return userRepository.findByUserUuid(userUuid).orElseThrow(()->BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST));
	}


	public User convertToUserEntity(SignUpDto signUpDto, TemporaryUser temporaryUser, User parent) {

		String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());

		User registUser = User.builder()
				.name(signUpDto.getName()) // SignUpDto에 name 필드가 있다고 가정
				.email(temporaryUser.getEmail())
				.password(encodedPassword)
				.gender(signUpDto.getGender())
				.address(signUpDto.getAddress())
				.address2(signUpDto.getAddress2())
				.telephone(temporaryUser.getTelephone())
				.birth(LocalDate.parse(signUpDto.getBirth())) // ISO 날짜 포맷으로 변경
				.build();

		if (parent != null) {
			registUser.addParent(parent); // 찾은 부모 사용자를 설정
		}

		return registUser;
	}

	public List<User> findMyKidsByParents(User parents){
		List<User> myKids = userRepository.findMyKidsByParents(parents);
		if(myKids.isEmpty())
			throw BusinessException.of(ErrorCode.API_ERROR_USER_NOT_EXIST_MY_KIDS);
		return myKids;
	}
}
