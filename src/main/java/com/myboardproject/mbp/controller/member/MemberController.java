package com.myboardproject.mbp.controller.member;

import com.myboardproject.mbp.controller.dto.MemberCreateRequestDto;
import com.myboardproject.mbp.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/signup")
    public String signup(MemberCreateRequestDto requestDto) {
        return "/member/signup";
    }

    @PostMapping("/member/signup")
    public String signup(@Valid MemberCreateRequestDto requestDto, BindingResult result) {
        if (result.hasErrors()) {
            return "/member/signup";
        }

        // 비밀번호 확인
        if (!requestDto.getPassword1().equals(requestDto.getPassword2())) {
            result.rejectValue("password2",
                    "passwordIncorrect",
                    "패스워드가 일치하지 않습니다.");
            return "/member/signup";
        }

        memberService.createMember(requestDto);
        return "redirect:/";
    }
}
