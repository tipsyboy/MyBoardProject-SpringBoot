package com.myboardproject.mbp.service.reply;

import com.myboardproject.mbp.controller.dto.member.MemberDto;
import com.myboardproject.mbp.controller.dto.post.PostSaveRequestDto;
import com.myboardproject.mbp.controller.dto.reply.ReplyResponseDto;
import com.myboardproject.mbp.controller.dto.reply.ReplySaveRequestDto;
import com.myboardproject.mbp.domain.member.Member;
import com.myboardproject.mbp.domain.member.MemberRepository;
import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.post.PostRepository;
import com.myboardproject.mbp.domain.reply.Reply;
import com.myboardproject.mbp.domain.reply.ReplyRepository;
import com.myboardproject.mbp.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    public ReplyResponseDto getReplyDto(Long id) {
        Reply findReply = replyRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 답변입니다."));

        return new ReplyResponseDto(findReply);
    }

    @Transactional
    public void createReply(Long postId, ReplySaveRequestDto requestDto, MemberDto memberDto) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 게시글"));
        Member findMember = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 사용자"));

        Reply reply = requestDto.toEntityAndMappingPost(findPost, findMember);
        replyRepository.save(reply);
    }

    @Transactional
    public void modify(Long id, ReplySaveRequestDto replySaveRequestDto) {
        Reply reply = replyRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("해당 댓글을 찾을 수 없습니다. id=" + id));

        reply.updateReply(replySaveRequestDto.getContent());
    }
}
