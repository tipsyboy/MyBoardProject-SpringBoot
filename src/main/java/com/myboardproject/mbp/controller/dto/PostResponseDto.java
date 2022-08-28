package com.myboardproject.mbp.controller.dto;

import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.reply.Reply;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostResponseDto {

    private String title;
    private String content;
    private List<Reply> replyList = new ArrayList<>();

    public PostResponseDto(Post entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.replyList = entity.getReplyList();
    }
}
