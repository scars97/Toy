package org.example.mealcrud.service;

import org.example.mealcrud.model.*;
import org.example.mealcrud.model.dto.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertAllImp implements InsertAll{

    private static final Map<String, Object> storage = new HashMap<>();

    @Override
    public Map<String, Object> insertAll(MultipleDtoRequest multipleDto) {
        CommonDto commonDto = multipleDto.getCommonDto();

        insertMtCorp(commonDto, multipleDto.getMtCorpDto());
        insertArea(commonDto, multipleDto.getAreaDto());
        insertProduct(commonDto, multipleDto.getProductDto());
        insertCategory(commonDto, multipleDto.getCategoryDto());
        insertStore(commonDto, multipleDto.getStoreDto());
        return storage;
    }

    public void insertMtCorp(CommonDto common, MtCorpDto mtCorpDto) {
        MtCorp mtCorp = MtCorp.builder()
                .clscd(common.getClscd())
                .corpCd(common.getCorpCd())
                .prefixNo(common.getPrefixNo())
                .eventNm(mtCorpDto.getEventNm())
                .ctgrCheckType(mtCorpDto.getCtgrCheckType())
                .prdtCheckType(mtCorpDto.getPrdtCheckType())
                .storeCheckType(mtCorpDto.getStoreCheckType())
                .areaCheckType(mtCorpDto.getAreaCheckType())
                .dateType(mtCorpDto.getDateType())
                .dateValues(mtCorpDto.getDateValues())
                .startTime(mtCorpDto.getStartTime())
                .endTime(mtCorpDto.getEndTime())
                .regUser(common.getRegUser())
                .regDate(common.getRegDate())
                .updUser(common.getUpdUser())
                .updDate(common.getUpdDate())
                .build();
        storage.put("식권사", mtCorp);
    }

    public void insertArea(CommonDto common, List<AreaDto> area) {
        List<InfoArea> infoAreas = new ArrayList<>();
        for (AreaDto areaDto : area) {
            InfoArea infoArea = InfoArea.builder()
                    .clscd(common.getClscd())
                    .corpCd(common.getCorpCd())
                    .prefixNo(common.getPrefixNo())
                    .regUser(common.getRegUser())
                    .regDate(common.getRegDate())
                    .updUser(common.getUpdUser())
                    .updDate(common.getUpdDate())
                    .sido(areaDto.getSido())
                    .gugun(areaDto.getGugun())
                    .build();
            infoAreas.add(infoArea);
        }
        storage.put("지역", infoAreas);
    }

    public void insertProduct(CommonDto common, List<ProductDto> product) {
        List<InfoProduct> infoProducts = new ArrayList<>();
        for (ProductDto productDto : product) {
            InfoProduct infoProduct = InfoProduct.builder()
                    .clscd(common.getClscd())
                    .corpCd(common.getCorpCd())
                    .prefixNo(common.getPrefixNo())
                    .regUser(common.getRegUser())
                    .regDate(common.getRegDate())
                    .updUser(common.getUpdUser())
                    .updDate(common.getUpdDate())
                    .productCd(productDto.getProductCd())
                    .build();
            infoProducts.add(infoProduct);
        }
        storage.put("상품", infoProducts);
    }

    public void insertCategory(CommonDto common, List<CategoryDto> category) {
        List<InfoCategory> infoCategories = new ArrayList<>();
        for (CategoryDto categoryDto : category) {
            InfoCategory infoCategory = InfoCategory.builder()
                    .clscd(common.getClscd())
                    .corpCd(common.getCorpCd())
                    .prefixNo(common.getPrefixNo())
                    .regUser(common.getRegUser())
                    .regDate(common.getRegDate())
                    .updUser(common.getUpdUser())
                    .updDate(common.getUpdDate())
                    .lgCode(categoryDto.getLgCode())
                    .mdCode(categoryDto.getMdCode())
                    .smCode(categoryDto.getSmCode())
                    .build();
            infoCategories.add(infoCategory);
        }
        storage.put("카테고리", infoCategories);
    }

    public void insertStore(CommonDto common, List<StoreDto> store) {
        List<InfoStore> infoStores = new ArrayList<>();
        for (StoreDto storeDto : store) {
            InfoStore infoStore = InfoStore.builder()
                    .clscd(common.getClscd())
                    .corpCd(common.getCorpCd())
                    .prefixNo(common.getPrefixNo())
                    .regUser(common.getRegUser())
                    .regDate(common.getRegDate())
                    .updUser(common.getUpdUser())
                    .updDate(common.getUpdDate())
                    .storeCd(storeDto.getStoreCd())
                    .build();
            infoStores.add(infoStore);
        }
        storage.put("점포", infoStores);
    }
}
