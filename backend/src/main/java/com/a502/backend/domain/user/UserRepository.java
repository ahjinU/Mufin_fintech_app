package com.a502.backend.domain.user;

import com.a502.backend.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional<User> findByEmail(String email);

    Optional<User> findByTelephone(String telephone);

    boolean existsByEmail(String email);

    @Query("select u from User u where u.userUuid = :userUuid")
    Optional<User> findByUserUuid(String userUuid);
}

