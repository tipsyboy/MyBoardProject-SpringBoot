package com.myboardproject.mbp.service.post;

import com.myboardproject.mbp.controller.dto.PostListReponseDto;
import com.myboardproject.mbp.controller.dto.PostSaveRequestDto;
import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    // 게시글 저장
    public Long savePost(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }


    // 모든 게시글 조회
    public List<PostListReponseDto> getPostList() {
        return postRepository.findAllPostDesc().stream()
                .map(Post -> new PostListReponseDto(Post))
                .collect(Collectors.toList());
    }

}
