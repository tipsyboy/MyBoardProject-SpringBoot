package com.myboardproject.mbp.controller.post;

import com.myboardproject.mbp.controller.dto.PostListReponseDto;
import com.myboardproject.mbp.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/")
    public String root() {
        return "redirect:/post/post/list";
    }

    @GetMapping("/post/list")
    public String postList(Model model) {
        List<PostListReponseDto> postList = postService.getPostList();
        model.addAttribute("postList", postList);

        return "/post/post_list";
    }

}
