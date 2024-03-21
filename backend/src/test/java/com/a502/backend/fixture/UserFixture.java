package com.a502.backend.fixture;

import com.a502.backend.application.entity.User;

import java.time.LocalDateTime;

public enum UserFixture {

    USER_PARENT("김부모", "hansl@ssafy.com", "ssafy", 1, null),
    USER_CHILD1("김아들", "eunpeong@ssafy.com", "ssafy", 2, null),
    USER_CHILD2("김딸", "rayeon@ssafy.com", "ssafy", 2, null),
    ;

    UserFixture(String name, String email, String password, int type, Integer parent) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
        this.parent = parent;
    }

    public User create(){
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .type(type)
                .parent(null)
                .build();
    }
    private final String name;
    private final String email;
    private final String password;
    private final int type;
//    private final String gender;
//    private final String address;
//    private final String address2;
//    private final String telephone;
//    private final LocalDateTime birth;
//    private final Short failed;
    private final Integer parent;


}
