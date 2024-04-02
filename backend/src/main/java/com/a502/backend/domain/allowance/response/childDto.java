package com.a502.backend.domain.allowance.response;

import com.a502.backend.application.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class childDto {
    private String name;
    private String childUuid;

    @Builder
    public childDto(String name, String childUuid) {
        this.name = name;
        this.childUuid = childUuid;
    }

    public static List<childDto> convertFromEntitys(List<User> childUser) {
        List<childDto> result = new ArrayList<>();

        for(User user : childUser){
            result.add(convertFromEntity(user));
        }

        return result;
    }

    public static childDto convertFromEntity(User user) {

        childDto result = childDto.builder()
                .childUuid(user.getUserUuid().toString())
                .name(user.getName())
                .build();
        return result;
    }


}
