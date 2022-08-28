package com.myboardproject.mbp.service.reply;

import com.myboardproject.mbp.controller.dto.ReplySaveRequestDto;
import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.post.PostRepository;
import com.myboardproject.mbp.domain.reply.Reply;
import com.myboardproject.mbp.domain.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public void createReply(Long postId, ReplySaveRequestDto requestDto) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글"));

        Reply reply = requestDto.toEntityAndMappingPost(findPost);
        replyRepository.save(reply);
    }
}
