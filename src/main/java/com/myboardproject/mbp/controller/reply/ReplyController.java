package com.myboardproject.mbp.controller.reply;

import com.myboardproject.mbp.controller.dto.member.MemberDto;
import com.myboardproject.mbp.controller.dto.post.PostResponseDto;
import com.myboardproject.mbp.controller.dto.reply.ReplyResponseDto;
import com.myboardproject.mbp.controller.dto.reply.ReplySaveRequestDto;
import com.myboardproject.mbp.service.member.MemberService;
import com.myboardproject.mbp.service.post.PostService;
import com.myboardproject.mbp.service.reply.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;
    private final PostService postService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/reply/create/{id}")
    public String createReply(Model model, @PathVariable Long id,
                              @Valid ReplySaveRequestDto requestDto, BindingResult result,
                              Principal principal) {

        if (result.hasErrors()) {
            PostResponseDto postResponseDto = postService.view(id);
            model.addAttribute("post", postResponseDto);
            return "/post/post_view";
        }

        MemberDto memberDto = memberService.getMemberDto(principal.getName());
        replyService.createReply(id, requestDto, memberDto);
        return String.format("redirect:/post/view/%s", id);
    }

    // ========== 댓글 수정하기 ==============
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/reply/modify/{id}")
    public String modifyReply(ReplySaveRequestDto replySaveRequestDto, @PathVariable("id") Long id,
                              Principal principal) {

        ReplyResponseDto replyDto = replyService.getReplyDto(id);
        if (!replyDto.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        replySaveRequestDto.mappingModifyInfo(replyDto);
        return "/reply/reply_modify_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/reply/modify/{id}")
    public String modifyReply(@Valid ReplySaveRequestDto replySaveRequestDto, BindingResult bindingResult,
                              @PathVariable("id") Long id,
                              Principal principal) {

        if (bindingResult.hasErrors()) {
            return "/reply/reply_modify_form";
        }

        ReplyResponseDto replyDto = replyService.getReplyDto(id);
        if (!replyDto.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        replyService.modify(id, replySaveRequestDto);
        return String.format("redirect:/post/view/%s", replyDto.getPostId());
    }
}
