package com.myboardproject.mbp.controller.dto;

import lombok.Getter;

@Getter
public class MemberDto {

    private Long id;
    private String username;
    private String password;
    private String email;

    public MemberDto(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
