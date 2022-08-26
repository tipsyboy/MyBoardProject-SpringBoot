package com.myboardproject.mbp.domain.member;

import com.myboardproject.mbp.domain.post.Post;
import com.myboardproject.mbp.domain.reply.Reply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "author")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Reply> replyList = new ArrayList<>();

    public Member(String username) {
        this.username = username;
    }

}
