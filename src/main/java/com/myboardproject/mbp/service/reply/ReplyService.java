package com.myboardproject.mbp.service.reply;

import com.myboardproject.mbp.controller.dto.MemberDto;
import com.myboardproject.mbp.controller.dto.ReplySaveRequestDto;
import com.myboardproject.mbp.domain.member.Member;
import com.myboardproject.mbp.domain.member.MemberRepository;
import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.post.PostRepository;
import com.myboardproject.mbp.domain.reply.Reply;
import com.myboardproject.mbp.domain.reply.ReplyRepository;
import com.myboardproject.mbp.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    public void createReply(Long postId, ReplySaveRequestDto requestDto, MemberDto memberDto) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 게시글"));
        Member findMember = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 사용자"));

        Reply reply = requestDto.toEntityAndMappingPost(findPost, findMember);
        replyRepository.save(reply);
    }
}
