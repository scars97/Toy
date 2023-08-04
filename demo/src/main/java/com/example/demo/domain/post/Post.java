package com.example.demo.domain.post;

import lombok.*;

@Getter
@NoArgsConstructor
public class Post {

    private Long id;

    private String title;

    private String author;

    private String content;

    @Builder
    public Post(Long id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
