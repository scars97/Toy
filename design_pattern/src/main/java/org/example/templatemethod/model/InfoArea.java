package org.example.templatemethod.model;

import lombok.Builder;
import lombok.Getter;

/**
 * 제어 정보 지역
 */
@Getter
@Builder
public class InfoArea extends Base {

    private String sido;
    private String gugun;
}
