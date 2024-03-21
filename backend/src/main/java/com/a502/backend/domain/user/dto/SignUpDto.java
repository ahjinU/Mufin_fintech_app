package com.a502.backend.domain.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private String name;
    private String password;
    private String gender;
    private String address;
    private String address2;
    private String birth;

}
