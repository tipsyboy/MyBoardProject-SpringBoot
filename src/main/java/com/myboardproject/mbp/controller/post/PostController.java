package com.myboardproject.mbp.controller.post;

import com.myboardproject.mbp.controller.dto.*;
import com.myboardproject.mbp.controller.dto.PostListResponseDto;
import com.myboardproject.mbp.service.member.MemberService;
import com.myboardproject.mbp.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/")
    public String root() {
        return "redirect:/post/list";
    }

    // 글 등록
    @PreAuthorize("isAuthenticated()") // Principal 객체가 null인 경우를 방지하기 위해서 로그인된 사용자만 글을 쓸 수 있도록 함
    @GetMapping("/post/create")
    public String createPost(PostSaveRequestDto requestDto) {
        return "/post/post_create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/create")
    public String createPost(@Valid PostSaveRequestDto requestDto, BindingResult result,
                             Principal principal) {

        if (result.hasErrors()) { // 바인딩 에러
            return "/post/post_create";
        }

        MemberDto memberDto = memberService.getMemberDto(principal.getName());
        postService.savePost(requestDto, memberDto);
        return "redirect:/post/list";
    }


    // 글 목록
    @GetMapping("/post/list")
    public String postList(Model model, @RequestParam(value="page", defaultValue = "0") int page) {
        Page<PostListResponseDto> postList = postService.getPostList(page);
        model.addAttribute("postList", postList);

        return "/post/post_list";
    }


    // 글 상세보기
    @GetMapping("/post/view/{id}")
    public String postView(Model model, @PathVariable("id") Long id, ReplySaveRequestDto replySaveRequestDto) {
        PostResponseDto postResponseDto = postService.view(id);
        model.addAttribute("post", postResponseDto);

        return "/post/post_view";
    }

}
