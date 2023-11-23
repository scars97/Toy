package org.example.templatemethod.model;

import lombok.Builder;
import lombok.Getter;

/**
 * 제어 정보 점포
 */
@Getter
@Builder
public class InfoStore extends Base {

    private String storeCd;
}
