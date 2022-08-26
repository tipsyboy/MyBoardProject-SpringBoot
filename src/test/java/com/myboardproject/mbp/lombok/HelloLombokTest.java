package com.myboardproject.mbp.lombok;

import com.myboardproject.mbp.controller.HelloLombok;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HelloLombokTest {

    @Test
    public void 롬복_기능_테스트() {

        // given
        String name = "Hello";
        int temp = 100;

        // when
        HelloLombok helloLombok = new HelloLombok(name, temp);

        // then
        assertThat(helloLombok.getName()).isEqualTo(name);
        assertThat(helloLombok.getTemp()).isEqualTo(temp);
    }
}