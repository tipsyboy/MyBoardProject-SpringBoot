package com.myboardproject.mbp.controller.post;

import com.myboardproject.mbp.controller.dto.post.PostListResponseDto;
import com.myboardproject.mbp.controller.dto.member.MemberDto;
import com.myboardproject.mbp.controller.dto.post.PostResponseDto;
import com.myboardproject.mbp.controller.dto.post.PostSaveRequestDto;
import com.myboardproject.mbp.controller.dto.reply.ReplySaveRequestDto;
import com.myboardproject.mbp.service.member.MemberService;
import com.myboardproject.mbp.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

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


    // ========== 글 수정하기 ==============
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/modify/{id}")
    public String modifyPost(PostSaveRequestDto requestDto, @PathVariable("id") Long id,
                             Principal principal) {

        PostResponseDto findPostDto = postService.view(id); // Service에서 예외처리하면 여기선 어떻게 되는것?
        if (!findPostDto.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        requestDto.mappingModifyInfo(findPostDto);
        return "/post/post_create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/post/modify/{id}")
    public String modifyPost(@Valid PostSaveRequestDto requestDto, BindingResult bindingResult,
                             @PathVariable("id") Long id,
                             Principal principal) {

        if (bindingResult.hasErrors()) {
            return "/post/post_create";
        }

        PostResponseDto findPostDto = postService.view(id);
        if (!findPostDto.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        postService.modify(id, requestDto);
        return String.format("redirect:/post/view/%s", id);
    }

    // ========== 글 삭제하기 ==============
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable("id") Long id,
                             Principal principal) {

        PostResponseDto findPostDto = postService.view(id);
        if (!findPostDto.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }

        postService.deletePost(id);
        return "redirect:/";
    }

}
