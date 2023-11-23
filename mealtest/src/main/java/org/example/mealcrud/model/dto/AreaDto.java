package org.example.mealcrud.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AreaDto {

    private String sido; // 시/도
    private String gugun; // 구/군
}
