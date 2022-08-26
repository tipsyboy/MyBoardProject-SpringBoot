package com.myboardproject.mbp.domain.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}