package com.myboardproject.mbp.controller.dto;

import com.myboardproject.mbp.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListReponseDto {

    private Long id;
    private String title;
//    private String author;
    private LocalDateTime lastModifiedDate;

    public PostListReponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
//        this.author = entity.getAuthor().getUsername();
        this.lastModifiedDate = entity.getLastModifiedDate();
    }
}
