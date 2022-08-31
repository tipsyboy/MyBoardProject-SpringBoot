package com.myboardproject.mbp.controller.dto;

import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.reply.Reply;
import com.sun.xml.bind.v2.TODO;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime lastModifiedDate;

//     TODO: 여기도 Reply Entity가 Controller에 노출되므로 처리 해줘야 할듯?
//    private List<Reply> replyList = new ArrayList<>();

    private List<ReplyResponseDto> replyList = new ArrayList<>();

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.lastModifiedDate = entity.getLastModifiedDate();
        this.replyList = entity.getReplyList().stream()
                .map(Reply -> new ReplyResponseDto(Reply))
                .collect(Collectors.toList());
    }
}
