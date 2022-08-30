package com.myboardproject.mbp.controller.post;

import com.myboardproject.mbp.controller.dto.PostListReponseDto;
import com.myboardproject.mbp.controller.dto.PostResponseDto;
import com.myboardproject.mbp.controller.dto.PostSaveRequestDto;
import com.myboardproject.mbp.controller.dto.ReplySaveRequestDto;
import com.myboardproject.mbp.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String root() {
        return "redirect:/post/list";
    }


    // 글 등록
    @GetMapping("/post/create")
    public String createPost(PostSaveRequestDto requestDto) {
        return "/post/post_create";
    }

    @PostMapping("/post/create")
    public String createPost(@Valid PostSaveRequestDto requestDto, BindingResult result) {
        if (result.hasErrors()) { // 바인딩 에러
            return "/post/post_create";
        }

        postService.savePost(requestDto);
        return "redirect:/post/list";
    }


    // 글 목록
    @GetMapping("/post/list")
    public String postList(Model model) {
        List<PostListReponseDto> postList = postService.getPostList();
        model.addAttribute("postList", postList);

        return "/post/post_list";
    }


    // 글 상세보기
    @GetMapping("/post/view/{id}")
    public String postView(Model model, @PathVariable("id") Long id, ReplySaveRequestDto replySaveRequestDto) {
        PostResponseDto postResponseDto = postService.view(id);
        model.addAttribute("post",postResponseDto);

        return "/post/post_view";
    }

}
