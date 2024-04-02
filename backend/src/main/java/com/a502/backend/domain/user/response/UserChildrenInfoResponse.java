package com.a502.backend.domain.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserChildrenInfoResponse {
    List<UserInfoResponse> children;
}
