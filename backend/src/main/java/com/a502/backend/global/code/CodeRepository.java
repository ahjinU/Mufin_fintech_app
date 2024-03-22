package com.a502.backend.global.code;

import com.a502.backend.application.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, String> {
	Optional<Code> findById(String id);

	Code findByName(String name);
}
