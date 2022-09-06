package com.myboardproject.mbp.domain.member;

import com.myboardproject.mbp.controller.dto.MemberCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Long createMember(MemberCreateRequestDto requestDto) {
        // 받은 password를 암호화한다.
        // MemberCreateRequestDto 를 Entity로 변환해서 DB에 저장한다.

        Member member = requestDto.toMemberEntity(passwordEncoder.encode(requestDto.getPassword1()));
        memberRepository.save(member);

        return member.getId();
    }
}

