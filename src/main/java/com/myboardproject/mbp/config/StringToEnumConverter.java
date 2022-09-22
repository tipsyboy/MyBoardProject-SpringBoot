package com.myboardproject.mbp.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.myboardproject.mbp.domain.post.PostCategory;
import org.springframework.core.convert.converter.Converter;



public class StringToEnumConverter implements Converter<String, PostCategory> {

    /*
        https://www.baeldung.com/spring-enum-request-param 참조
     */

    @Override
    public PostCategory convert(String source) {
        try {
            return PostCategory.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
