package com.example.demo.service;

import com.example.demo.domain.api.ApiDataDto;
import com.example.demo.domain.api.ApiRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {

    private final ApiRepository apiRepository;

    //api 연동 예제 데이터 전송
    public String sendData() {

        String apiUrl = "http://localhost:8081";
        String clickKey = UUID.randomUUID().toString();

        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .path("/cookie-oven")
                .queryParam("click_key", clickKey)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        return response.getBody();
    }

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
