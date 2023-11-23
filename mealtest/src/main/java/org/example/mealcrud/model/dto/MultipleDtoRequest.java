package org.example.mealcrud.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MultipleDtoRequest {

    private CommonDto commonDto;
    private MtCorpDto mtCorpDto;
    private List<AreaDto> areaDto;
    private List<ProductDto> productDto;
    private List<CategoryDto> categoryDto;
    private List<StoreDto> storeDto;
}
