package com.myboardproject.mbp.controller.dto;

import com.myboardproject.mbp.domain.reply.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponseDto {

    private String content;
    private LocalDateTime lastModifiedDate;

    public ReplyResponseDto(Reply entity) {
        this.content = entity.getContent();
        this.lastModifiedDate = entity.getLastModifiedDate();
    }

}
