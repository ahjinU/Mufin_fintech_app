package com.a502.backend.application.entity;

import com.a502.backend.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "temporary_users")
public class TemporaryUser  extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "temporary_user_id")
    private int id;

    @Column(name = "temporary_user_uuid")
    private UUID temporaryUserUuid;

    @PrePersist
    public void initUUID() {
        if (temporaryUserUuid == null)
            temporaryUserUuid = UUID.randomUUID();
    }

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;


    @Builder
    public TemporaryUser(int id, UUID temporaryUserUuid, String telephone, String email) {
        this.id = id;
        this.temporaryUserUuid = temporaryUserUuid;
        this.telephone = telephone;
        this.email = email;
    }

    // 이메일 필드 업데이트 메서드
    public void updateEmail(String email) {
        this.email = email;
    }
}