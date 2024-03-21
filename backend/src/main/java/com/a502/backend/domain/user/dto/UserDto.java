package com.a502.backend.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 10, max = 50)
    private String email;

    @NotNull
    @Size(min = 10, max = 50)
    private String address;

    @NotNull
    @Size(min = 10, max = 50)
    private String address2;

    @NotNull
    private int type;

    @NotNull
    @Size(min = 2, max = 3)
    private String gender;



}
