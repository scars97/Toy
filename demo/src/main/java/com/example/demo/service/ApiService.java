package com.example.demo.service;

import com.example.demo.domain.api.ApiDataDto;
import com.example.demo.domain.api.ApiRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {

    private final ApiRepository apiRepository;

    //api 연동 예제 데이터 저장
    public String apiDataSave(String result) throws JsonProcessingException {
        //Map<String, Object> apiData = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> apiData = mapper.readValue(result, HashMap.class);

        ApiDataDto apiDataDto = apiRepository.apiSave(apiData);
        if (apiDataDto == null) {
            throw new IllegalArgumentException("저장 오류");
        }
        return "{}";
    }

    public ApiDataDto findByApiData(Long id) {
        ApiDataDto findOne = apiRepository.findByApiId(id);
        if (findOne == null) {
            throw new IllegalArgumentException("해당 api 데이터 없음");
        }
        return findOne;
    }
}
