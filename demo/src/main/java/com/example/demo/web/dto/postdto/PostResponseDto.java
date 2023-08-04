package com.example.demo.web.dto.postdto;

import com.example.demo.domain.post.Post;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
    }
}
