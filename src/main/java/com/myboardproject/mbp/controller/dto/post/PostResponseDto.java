package com.myboardproject.mbp.controller.dto.post;

import com.myboardproject.mbp.controller.dto.reply.ReplyResponseDto;
import com.myboardproject.mbp.domain.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime lastModifiedDate;

//     TODO: 여기도 Reply Entity가 Controller에 노출되므로 처리 해줘야 할듯?
//    private List<Reply> replyList = new ArrayList<>();
    private List<ReplyResponseDto> replyList = new ArrayList<>();

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor().getUsername();
        this.lastModifiedDate = entity.getLastModifiedDate();
        this.replyList = entity.getReplyList().stream()
                .map(Reply -> new ReplyResponseDto(Reply))
                .collect(Collectors.toList());
    }
}
