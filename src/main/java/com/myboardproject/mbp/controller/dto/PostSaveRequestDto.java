package com.myboardproject.mbp.controller.dto;

import com.myboardproject.mbp.domain.member.Member;
import com.myboardproject.mbp.domain.post.Post;
import lombok.Getter;

@Getter
public class PostSaveRequestDto {

    private String title;
    private String content;
    private String author;

    public PostSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Post toEntity() {
        return new Post(title, content);
    }
}
