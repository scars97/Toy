package org.example.templatemethod.model;

import lombok.Builder;
import lombok.Getter;

/**
 * 제어정보 상품
 */
@Getter
@Builder
public class InfoProduct extends Base{

    private String productCd;
}
