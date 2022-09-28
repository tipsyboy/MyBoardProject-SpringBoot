package com.myboardproject.mbp.service.post;

import com.myboardproject.mbp.config.StringToEnumConverter;
import com.myboardproject.mbp.controller.dto.member.MemberDto;
import com.myboardproject.mbp.controller.dto.post.PostListResponseDto;
import com.myboardproject.mbp.controller.dto.post.PostResponseDto;
import com.myboardproject.mbp.controller.dto.post.PostSaveRequestDto;
import com.myboardproject.mbp.domain.member.Member;
import com.myboardproject.mbp.domain.member.MemberRepository;
import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.post.PostCategory;
import com.myboardproject.mbp.domain.post.PostRepository;
import com.myboardproject.mbp.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    // 게시글 저장
    @Transactional
    public void savePost(PostSaveRequestDto requestDto, MemberDto memberDto) {

        Member findMember = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new DataNotFoundException("사용자를 찾을 수 없습니다."));

        Post post = requestDto.toEntity(findMember);
        postRepository.save(post);
    }


    // 모든 게시글 조회
    public Page<PostListResponseDto> getPostList(int page, String category) {
//        return postRepository.findAllPostDesc().stream()
//                .map(Post -> new PostListResponseDto(Post))
//                .collect(Collectors.toList());

        // create Pageable object descending order
        Pageable pageable = PageRequest.of(page, 15, Sort.by("createdDate").descending());

//        return postRepository.findAll(pageable)
//                .map(Post -> new PostListResponseDto(Post));

        PostCategory categoryEnum = new StringToEnumConverter().convert(category);
        return postRepository.findAllByCategory(pageable, categoryEnum)
                .map(Post -> new PostListResponseDto(Post));
    }


    // 게시글 조회 및 Dto 반환
    public PostResponseDto view(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return new PostResponseDto(post);
    }


    // 게시글 수정
    @Transactional
    public void modify(Long id, PostSaveRequestDto requestDto) {
        Post findPost = postRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("해당 게시글을 찾을 수 없습니다. id=" + id));

        findPost.updatePost(requestDto.getTitle(), requestDto.getContent());
    }


    // 게시글 삭제
    @Transactional
    public void delete(Long id) {
        Post findPost = postRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("해당 게시글을 찾을 수 없습니다 id=" + id));
        postRepository.delete(findPost);
    }

    public String getBoardName(String category) {
        String boardName = "";
        switch (category) {
            case "free":
                boardName = "자유게시판";
                break;
            case "game":
                boardName = "게임판";
                break;
            case "food":
                boardName = "음식판";
                break;
        }
        return boardName;
    }
}
