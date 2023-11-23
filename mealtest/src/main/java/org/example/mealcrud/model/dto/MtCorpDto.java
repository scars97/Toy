package org.example.mealcrud.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MtCorpDto {

    private String eventNm; // 행사명
    private String ctgrCheckType; // 카테고리 체크 구분
    private String prdtCheckType; // 상품 체크 구분
    private String storeCheckType; // 점포 체크 구분
    private String areaCheckType; // 지역 체크 구분
    private String dateType; // 날짜 구분
    private String dateValues; // 날짜 값
    private String startTime; // 시작 시간
    private String endTime; // 종료 시간
}
