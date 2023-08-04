package com.example.demo.domain.api;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ApiRepository {

    private static final Map<Long, ApiDataDto> apiStore = new HashMap<>();
    private static long apiSequence = 0L;

    public ApiDataDto apiSave(Map<String, Object> apiData) {
        ApiDataDto resultData = ApiDataDto.builder()
                .id(++apiSequence)
                .advertiserToken((String) apiData.get("advertiser_token"))
                .clickKey((String) apiData.get("click_key"))
                .build();
        apiStore.put(resultData.getId(), resultData);
        return resultData;
    }

    public ApiDataDto findByApiId(Long id) {
        return apiStore.get(id);
    }
}
