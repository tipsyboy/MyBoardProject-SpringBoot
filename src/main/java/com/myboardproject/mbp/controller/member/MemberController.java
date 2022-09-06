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

    private MemberService memberService;

    @GetMapping("/member/signup")
    public String signup(MemberCreateRequestDto requestDto) {
        return "/member/signup";
    }

    // TODO: SIGN-UP POST 구현
    // TODO: form binding check
    // TODO: PASSWORD 확인
    // TODO: Service에 create 요청
}
