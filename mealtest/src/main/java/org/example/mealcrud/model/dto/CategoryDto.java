package org.example.mealcrud.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryDto {

    private String lgCode; // 대분류 코드
    private String mdCode; // 중분류 코드
    private String smCode; // 소분류 코드
}
