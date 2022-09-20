package com.myboardproject.mbp.domain.member;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    MEMBER("MEMBER_ROLE");

    private String value;

    MemberRole(String value) {
        this.value = value;
    }
}
