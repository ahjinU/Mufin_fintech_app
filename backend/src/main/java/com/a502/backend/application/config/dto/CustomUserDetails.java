package com.a502.backend.application.config.dto;

import com.a502.backend.application.entity.User;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@ToString
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한을 반환합니다. 예제에서는 간단히 처리합니다.
        // 실제로는 user.getRoles() 같은 메소드로 사용자의 권한을 조회하여 처리해야 합니다.
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        // 사용자의 비밀번호를 반환합니다.
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // 사용자의 이름 또는 사용자 식별자를 반환합니다.
        return user.getEmail(); // 또는 user.getName(), 사용자의 고유 식별 정보를 반환
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부를 반환합니다. 여기서는 항상 true로 설정합니다.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠김 여부를 반환합니다. 여기서는 항상 true로 설정합니다.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명(비밀번호) 만료 여부를 반환합니다. 여기서는 항상 true로 설정합니다.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부를 반환합니다. 사용자 엔티티에 이에 해당하는 필드가 있다면 사용합니다.
        // 예를 들어 user.isEnabled()와 같은 메소드를 호출할 수 있습니다.
        return true;
    }
}
