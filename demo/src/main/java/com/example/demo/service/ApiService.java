package com.example.demo.service;

import com.example.demo.domain.api.ApiDataDto;
import com.example.demo.domain.api.ApiRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {

    private final ApiRepository apiRepository;

    //api 연동 예제 데이터 전송
    public String sendData() {

        URI uri = getUri();
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        return response.getBody();
    }

    public String httpSendData() throws IOException {
        CloseableHttpClient httpClient = null;
        URI uri = getUri();

        try {
            httpClient = HttpClientBuilder.create().build();

            HttpGet get = new HttpGet(uri);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(2 * 1000)
                    .build();
            get.setConfig(requestConfig);

            CloseableHttpResponse httpResponse = httpClient.execute(get);

            return EntityUtils.toString(httpResponse.getEntity());

        } catch (IOException e){
            log.info("http 통신 오류");
        } finally {
            httpClient.close();
        }

        return null;
    }

    //api 연동 예제 데이터 저장
    public String saveTokenAndKey(String token, String clickKey) throws JsonProcessingException {

        ApiDataDto apiDataDto = apiRepository.saveTokenAndKey(token, clickKey);
        if (apiDataDto == null) {
            throw new IllegalArgumentException("저장 오류");
        }
        return "{}";
    }

    //저장된 api 데이터 가져오기
    public ApiDataDto findById(Long id) {
        ApiDataDto findOne = apiRepository.findById(id);
        if (findOne == null) {
            throw new IllegalArgumentException("해당 api 데이터 없음");
        }
        return findOne;
    }

    private URI getUri() {
        String apiUrl = "http://localhost:8081";
        String clickKey = UUID.randomUUID().toString();

        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .path("/cookie-oven")
                .queryParam("click_key", clickKey)
                .encode()
                .build()
                .toUri();

        return uri;
    }
}
