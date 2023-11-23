package org.example.mealcrud.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 제어 정보 카테고리
 */
@Getter
@Builder
public class InfoCategory {
    private String clscd; // 제어 코드
    private String corpCd; // 식권사 코드
    private String prefixNo; // 프리픽스 번호

    private String lgCode; // 대분류 코드
    private String mdCode; // 중분류 코드
    private String smCode; // 소분류 코드

    private String regUser; // 생성자
    private LocalDateTime regDate; // 생성일
    private String updUser; // 수정자
    private LocalDateTime updDate; // 수정일
}
