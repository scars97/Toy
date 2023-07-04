package com.example.demo.web.dto;

import com.example.demo.domain.Post;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostListResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;

    public PostListResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
    }
}
