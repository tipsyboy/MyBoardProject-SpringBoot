package com.myboardproject.mbp.controller.dto;

import lombok.Getter;

@Getter
public class MemberCreateRequestDto {

    private String username;
    private String password;
    private String email;

    public MemberCreateRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

}
