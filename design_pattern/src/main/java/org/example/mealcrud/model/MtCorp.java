package org.example.templatemethod.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * 식권사 제어 코드
 */
@Getter
@Builder
@ToString
public class MtCorp {

    private String clscd; // 제어 코드
    private String corpCd; // 식권사 코드
    private String prefixNo; // 프리픽스 번호

    private String eventNm; // 행사명
    private String ctgrCheckType; // 카테고리 체크 구분
    private String prdtCheckType; // 상품 체크 구분
    private String storeCheckType; // 점포 체크 구분
    private String areaCheckType; // 지역 체크 구분
    private String dateType; // 날짜 구분
    private String dateValues; // 날짜 값
    private String startTime; // 시작 시간
    private String endTime; // 종료 시간

    private String regUser; // 생성자
    private LocalDateTime regDate; // 생성일
    private String updUser; // 수정자
    private LocalDateTime updDate; // 수정일
}
