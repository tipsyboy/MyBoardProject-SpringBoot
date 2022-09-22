package com.myboardproject.mbp.service.post;

import com.myboardproject.mbp.controller.dto.member.MemberDto;
import com.myboardproject.mbp.controller.dto.post.PostListResponseDto;
import com.myboardproject.mbp.controller.dto.post.PostSaveRequestDto;
import com.myboardproject.mbp.domain.member.Member;
import com.myboardproject.mbp.domain.member.MemberRepository;
import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.post.PostCategory;
import com.myboardproject.mbp.domain.post.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired private PostRepository postRepository;
    @Autowired private PostService postService;
    @Autowired private MemberRepository memberRepository;


}