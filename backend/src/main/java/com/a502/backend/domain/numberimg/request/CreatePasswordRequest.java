package com.a502.backend.domain.numberimg.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreatePasswordRequest {
    String accountNumberOut;
    List<Integer> password;
}
