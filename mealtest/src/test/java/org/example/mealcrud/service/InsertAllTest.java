package org.example.mealcrud.service;

import org.example.mealcrud.model.Common;
import org.example.mealcrud.model.InfoProduct;
import org.example.mealcrud.model.TestModel;
import org.example.mealcrud.model.dto.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InsertAllTest {

    private final InsertAll insertAll = new InsertAllImp();

    @Test
    void insertAll() {
        // given
        MultipleDtoRequest multipleDtoRequest = create();

        // when
        Map<String, Object> result = insertAll.insertAll(multipleDtoRequest);

        // then
        List<InfoProduct> infoProduct = (List<InfoProduct>) result.get("상품");
        assertEquals(infoProduct.get(0).getProductCd(), "상품 코드");
    }

    @Test
    void testModel() {
        Common common = Common.builder()
                .clscd("제어코드")
                .corpCd("식권사코드")
                .prefixNo("프리픽스번호")
                .regUser("성현")
                .regDate(LocalDateTime.now())
                .updUser("성현")
                .updDate(LocalDateTime.now())
                .build();
        TestModel test = TestModel.builder()
                .common(common)
                .test("test")
                .build();

        assertEquals(test.getCommon().getClscd(), common.getClscd());
    }

    private MultipleDtoRequest create() {
        CommonDto common = CommonDto.builder()
                .clscd("제어코드")
                .corpCd("식권사코드")
                .prefixNo("프리픽스번호")
                .regUser("성현")
                .regDate(LocalDateTime.now())
                .updUser("성현")
                .updDate(LocalDateTime.now())
                .build();

        MtCorpDto mtCorp = MtCorpDto.builder()
                .eventNm("행사명")
                .ctgrCheckType("카테고리체크")
                .prdtCheckType("상품체크")
                .storeCheckType("점포체크")
                .areaCheckType("지역체크")
                .dateType("날짜구분")
                .dateValues("날짜값")
                .startTime("시작시간")
                .endTime("종료시간")
                .build();

        List<CategoryDto> categoryDtos = new ArrayList<>();
        CategoryDto category = CategoryDto.builder()
                .lgCode("대분류")
                .mdCode("중분류")
                .smCode("소분류")
                .build();
        categoryDtos.add(category);

        List<StoreDto> storeDtos = new ArrayList<>();
        StoreDto store = StoreDto.builder()
                .storeCd("점포코드")
                .build();
        storeDtos.add(store);


        List<AreaDto> areaDtos = new ArrayList<>();
        AreaDto area = AreaDto.builder()
                .sido("시도")
                .gugun("구군")
                .build();
        areaDtos.add(area);

        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto product = ProductDto.builder()
                .productCd("상품 코드")
                .build();
        productDtos.add(product);

        return MultipleDtoRequest.builder()
                .commonDto(common)
                .mtCorpDto(mtCorp)
                .areaDto(areaDtos)
                .productDto(productDtos)
                .categoryDto(categoryDtos)
                .storeDto(storeDtos)
                .build();
    }
}