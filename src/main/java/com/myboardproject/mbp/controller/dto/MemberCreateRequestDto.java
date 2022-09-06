package com.myboardproject.mbp.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class MemberCreateRequestDto {

    @Size(min = 3, max = 15)
    @NotEmpty(message = "ID를 입력해주세요.")
    private String username;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인을 입력해주세요.")
    private String password2;

    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    public MemberCreateRequestDto(String username, String password, String email) {
        this.username = username;
        this.password1 = password;
        this.email = email;
    }

}
