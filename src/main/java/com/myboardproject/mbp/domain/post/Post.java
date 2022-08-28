package com.myboardproject.mbp.domain.post;

import com.myboardproject.mbp.domain.BaseTimeEntity;
import com.myboardproject.mbp.domain.member.Member;
import com.myboardproject.mbp.domain.reply.Reply;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String title; // 글 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member author;

    @OneToMany(mappedBy = "post")
    private List<Reply> replyList = new ArrayList<>();

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // ========
    public void addReply(Reply reply) {
        replyList.add(reply);
    }
}
