package com.myboardproject.mbp.service.post;

import com.myboardproject.mbp.controller.dto.PostListResponseDto;
import com.myboardproject.mbp.controller.dto.PostListResponseDto;
import com.myboardproject.mbp.controller.dto.PostSaveRequestDto;
import com.myboardproject.mbp.domain.member.Member;
import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.post.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired private PostRepository postRepository;
    @Autowired private PostService postService;

    @Test
    public void 게시글_저장하기() {

        // given
        String title = "title";
        String content = "content";
        Member member = new Member("member1");

        PostSaveRequestDto requestDto = new PostSaveRequestDto(title, content, member.getUsername());
        postService.savePost(requestDto);

        // when
        List<Post> postList = postRepository.findAll();
        Post findPost = postList.get(0);

        // then
        assertThat(findPost.getTitle()).isEqualTo(title);
        assertThat(findPost.getContent()).isEqualTo(content);
    }

    @Test
    public void 게시글_조회() {
        Page<PostListResponseDto> postList = postService.getPostList(0);
        for (PostListResponseDto responseDto : postList) {
            System.out.println(responseDto.getId());
            System.out.println(responseDto.getTitle());
            System.out.println(responseDto.getLastModifiedDate());
        }
    }
}