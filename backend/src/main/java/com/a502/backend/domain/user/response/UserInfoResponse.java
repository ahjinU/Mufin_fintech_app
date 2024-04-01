package com.a502.backend.domain.user.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserInfoResponse {
    String userUuid;
    String name;
    String email;
    @JsonProperty(value = "isParent")
    Boolean isParent;
    Date createdAt;
    String address;
    String address2;
    String telephone;
    String accountNumber;
    String accountUuid;
}
