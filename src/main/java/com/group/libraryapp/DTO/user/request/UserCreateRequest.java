package com.group.libraryapp.DTO.user.request;

public class UserCreateRequest {

    private String name;
    private Integer age; // Integer은 null가능


    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
