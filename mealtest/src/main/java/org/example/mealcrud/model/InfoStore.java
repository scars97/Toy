package org.example.mealcrud.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 제어 정보 점포
 */
@Getter
@Builder
public class InfoStore {

    private String clscd; // 제어 코드
    private String corpCd; // 식권사 코드
    private String prefixNo; // 프리픽스 번호

    private String storeCd;

    private String regUser; // 생성자
    private LocalDateTime regDate; // 생성일
    private String updUser; // 수정자
    private LocalDateTime updDate; // 수정일
}
