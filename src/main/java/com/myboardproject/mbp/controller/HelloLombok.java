package com.myboardproject.mbp.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class HelloLombok {

    private String name;
    private int temp;

    public HelloLombok(String name, int temp) {
        this.name = name;
        this.temp = temp;
    }
}
