package com.myboardproject.mbp.controller.dto;

import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.reply.Reply;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class ReplySaveRequestDto {

    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;

    public ReplySaveRequestDto(String content) {
        this.content = content;
    }

    public Reply toEntityAndMappingPost(Post post) {
        Reply reply = new Reply(content, post);
        post.addReply(reply);
        return reply;
    }
}
