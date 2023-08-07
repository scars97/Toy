package com.example.demo.domain.api;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ApiRepository {

    private static final Map<Long, ApiDataDto> apiStore = new HashMap<>();
    private static long apiSequence = 0L;

    public String saveTokenAndKey(String token, String clickKey) {
        ApiDataDto resultData = ApiDataDto.builder()
                .id(++apiSequence)
                .advertiserToken(token)
                .clickKey(clickKey)
                .build();
        apiStore.put(resultData.getId(), resultData);
        return "{}";
    }

    public ApiDataDto findById(Long id) {
        return apiStore.get(id);
    }
}
