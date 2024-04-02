package com.a502.backend.domain.user;

import com.a502.backend.application.entity.TemporaryUser;
import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TemporaryUserRepository extends JpaRepository<TemporaryUser, Integer> {
    Optional<TemporaryUser> findByTelephone(String telephone);

    Optional<TemporaryUser> findByTemporaryUserUuid(UUID temporaryUserUuid);

    boolean existsByEmail(String email);
}
