package com.myboardproject.mbp.controller.dto.reply;

import com.myboardproject.mbp.domain.reply.Reply;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {

    private String content;
    private String author;
    private LocalDateTime lastModifiedDate;

    public ReplyResponseDto(Reply entity) {
        this.content = entity.getContent();
        this.author = entity.getAuthor().getUsername();
        this.lastModifiedDate = entity.getLastModifiedDate();
    }

}
