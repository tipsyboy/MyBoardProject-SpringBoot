package com.myboardproject.mbp.controller.dto;

import com.myboardproject.mbp.domain.post.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private String title;
    private String content;

    public PostResponseDto(Post entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
    }
}
