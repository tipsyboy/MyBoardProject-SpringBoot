package com.myboardproject.mbp.controller.reply;

import com.myboardproject.mbp.controller.dto.PostResponseDto;
import com.myboardproject.mbp.controller.dto.ReplySaveRequestDto;
import com.myboardproject.mbp.service.post.PostService;
import com.myboardproject.mbp.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;
    private final PostService postService;

    @PostMapping("/reply/create/{id}")
    public String createReply(Model model, @PathVariable Long id,
                              @Valid ReplySaveRequestDto requestDto, BindingResult result) {

        if (result.hasErrors()) {
            PostResponseDto postResponseDto = postService.view(id);
            model.addAttribute("post", postResponseDto);
            return "/post/post_view";
        }

        replyService.createReply(id, requestDto);
        return String.format("redirect:/post/view/%s", id);
    }
}
