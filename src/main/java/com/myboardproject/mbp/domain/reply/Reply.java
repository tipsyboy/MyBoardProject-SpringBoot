package com.myboardproject.mbp.domain.reply;

import com.myboardproject.mbp.domain.BaseTimeEntity;
import com.myboardproject.mbp.domain.post.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Reply extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Reply(String content, String author) {
        this.content = content;
        this.author = author;
    }
}
