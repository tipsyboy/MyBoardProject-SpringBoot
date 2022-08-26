package com.myboardproject.mbp.domain.post;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String subject; // 글 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    private String author; // 글쓴이

    public Post(Long id, String subject, String content, String author) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.author = author;
    }

}
