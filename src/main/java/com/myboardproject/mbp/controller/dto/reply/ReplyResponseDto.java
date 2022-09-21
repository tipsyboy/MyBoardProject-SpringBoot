package com.myboardproject.mbp.controller.dto.reply;

import com.myboardproject.mbp.domain.reply.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {

    private Long id;
    private String content;
    private String author;
    private LocalDateTime lastModifiedDate;
    private Long postId;

    public ReplyResponseDto(Reply entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.author = entity.getAuthor().getUsername();
        this.lastModifiedDate = entity.getLastModifiedDate();
        this.postId = entity.getPost().getId();
    }

}
