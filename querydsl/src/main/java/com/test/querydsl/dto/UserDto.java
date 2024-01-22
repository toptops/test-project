package com.test.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private String name;
    private int age;

    @QueryProjection
    public UserDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
