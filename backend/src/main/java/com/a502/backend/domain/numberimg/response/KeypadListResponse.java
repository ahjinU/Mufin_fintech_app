package com.a502.backend.domain.numberimg.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class KeypadListResponse {
    List<String> keypad;
}
