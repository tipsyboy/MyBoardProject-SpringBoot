package com.myboardproject.mbp.domain.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void 글_저장_불러오기() {

        // given
        String title = "테스트 제목";
        String content = "테스트 본문";
        String author = "김치파워맨";
        Post post = new Post(title, content, author);
        postRepository.save(post);

        // when
        List<Post> posts = postRepository.findAll();

        // then
        Post findPost = posts.get(0);
        assertThat(findPost.getTitle()).isEqualTo(title);
        assertThat(findPost.getContent()).isEqualTo(content);
        assertThat(findPost.getAuthor()).isEqualTo(author);
    }


    @Test
    public void BaseTimeEntity_확인() {

        // given
        Post post = new Post("title", "content", "파워김치맨");
        LocalDateTime now = LocalDateTime.of(2022, 8, 26, 0, 0, 0);

        // when
        List<Post> posts = postRepository.findAll();

        //then
        Post findPost = posts.get(0);
        System.out.println("==========> createdDate="+ findPost.getCreatedDate());
        System.out.println("==========> lastModifiedDate="+ findPost.getLastModifiedDate());
        assertThat(findPost.getCreatedDate()).isAfter(now);
        assertThat(findPost.getLastModifiedDate()).isAfter(now);
    }
}