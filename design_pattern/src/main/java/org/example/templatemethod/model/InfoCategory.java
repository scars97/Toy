package org.example.templatemethod.model;

import lombok.Builder;
import lombok.Getter;

/**
 * 제어 정보 카테고리
 */
@Getter
@Builder
public class InfoCategory extends Base {
    private String lgCode; // 대분류 코드
    private String mdCode; // 중분류 코드
    private String smCode; // 소분류 코드
}
