package com.example.demo.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiDataDto {

    private Long id;
    private String advertiserToken;
    private String clickKey;

    @Builder
    public ApiDataDto(Long id, String advertiserToken, String clickKey) {
        this.id = id;
        this.advertiserToken = advertiserToken;
        this.clickKey = clickKey;
    }
}
