package com.myboardproject.mbp.service.post;

import com.myboardproject.mbp.controller.dto.PostListResponseDto;
import com.myboardproject.mbp.controller.dto.PostResponseDto;
import com.myboardproject.mbp.controller.dto.PostSaveRequestDto;
import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public Page<PostListResponseDto> getPostList(int page) {
//        return postRepository.findAllPostDesc().stream()
//                .map(Post -> new PostListReponseDto(Post))
//                .collect(Collectors.toList());

        // create Pageable object descending order
        Pageable pageable = PageRequest.of(page, 20, Sort.by("createdDate").descending());
        return postRepository.findAll(pageable)
                .map(Post -> new PostListResponseDto(Post));
    }

    // 게시글 조회
    public PostResponseDto view(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return new PostResponseDto(post);
    }

}
