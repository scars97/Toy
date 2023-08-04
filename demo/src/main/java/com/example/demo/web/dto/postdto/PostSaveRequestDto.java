package com.example.demo.web.dto.postdto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostSaveRequestDto {

    private String title;
    private String author;
    private String content;

    @Builder
    public PostSaveRequestDto(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }
}
