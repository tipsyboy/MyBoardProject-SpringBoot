package com.myboardproject.mbp.controller.dto.post;

import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.reply.Reply;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime lastModifiedDate;
    private Integer replyCnt;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor().getUsername();
        this.lastModifiedDate = entity.getLastModifiedDate();
        this.replyCnt = entity.getReplyList().size();
    }
}
