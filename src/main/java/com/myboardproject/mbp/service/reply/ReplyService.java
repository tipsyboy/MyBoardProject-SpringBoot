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

import java.util.Arrays;
import java.util.List;

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

    @Transactional
    public void delete(Long id) {
        Reply findReply = replyRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("해당 댓글을 찾을 수 없습니다. id=" + id));

        replyRepository.delete(findReply);
        /**
         * TODO: 삭제한 글/댓글은 JAVA코드에 의해서 Member Entity의 list에 넣었었는데, 삭제 이후 어떻게 되는거지
         * TODO: 없어지긴 했는데, 잘 모르겠음.
         */
    }
}
