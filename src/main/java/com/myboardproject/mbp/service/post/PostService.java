package com.myboardproject.mbp.service.post;

import com.myboardproject.mbp.controller.dto.PostSaveRequestDto;
import com.myboardproject.mbp.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public Long savePost(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }
}
