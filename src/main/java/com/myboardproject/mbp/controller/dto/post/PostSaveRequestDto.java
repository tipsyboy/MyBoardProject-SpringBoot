package com.myboardproject.mbp.controller.dto.post;

import com.myboardproject.mbp.domain.member.Member;
import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.post.PostCategory;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class PostSaveRequestDto {

    @NotEmpty(message = "제목은 필수 항목입니다.")
    @Size(max = 200)
    private String title;

    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;

    private String author;
    private PostCategory category;


    public PostSaveRequestDto(String title, String content, String author, PostCategory category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }


    public Post toEntity(Member member) {
        Post post = new Post(this.title, this.content, member, this.category);
        member.addPostMember(post);
        return post;
    }


    public void mappingModifyInfo(PostResponseDto postResponseDto) {
        this.title = postResponseDto.getTitle();
        this.content = postResponseDto.getContent();
    }
}
