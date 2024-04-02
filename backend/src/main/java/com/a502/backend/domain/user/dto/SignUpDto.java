package com.a502.backend.domain.user.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class SignUpDto {

    private  String temporaryUserUuid;
    private String name;
    private String password;
    private String gender;
    private String address;
    private String address2;
    private String birth;

    @Builder

    public SignUpDto(String temporaryUserUuid, String name, String password, String gender, String address, String address2, String birth) {
        this.temporaryUserUuid = temporaryUserUuid;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.address2 = address2;
        this.birth = birth;
    }
}
