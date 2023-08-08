package com.example.demo.service;

import com.example.demo.domain.api.ApiDataDto;
import com.example.demo.domain.api.ApiRepository;
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
    private String click_key;

    //api 연동 예제 데이터 전송
    public String sendData() {

        URI uri = getUri();
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        return response.getBody();
    }

    public String httpSendData() throws IOException {
        CloseableHttpClient httpClient = null;
        String result = null;
        URI uri = getUri();

        try {
            httpClient = HttpClientBuilder.create().build();

            HttpGet get = new HttpGet(uri);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(2 * 1000)
                    .build();
            get.setConfig(requestConfig);

            CloseableHttpResponse httpResponse = httpClient.execute(get);

            result = EntityUtils.toString(httpResponse.getEntity());

        } catch (IOException e){
            log.info("http 통신 오류");
        } finally {
            httpClient.close();
        }

        return result;
    }

    //api 연동 예제 데이터 저장
    public String saveTokenAndKey(String token, String clickKey) {
        if(clickKey.equals(click_key)){
            log.info("전달한 click_key 값과 동일");
            return apiRepository.saveTokenAndKey(token, clickKey);
        } else {
            log.info("전달한 click_key 값과 불일치");

            throw new IllegalArgumentException("잘못된 파라미터입니다.");
//            String errorMessage = "{\"code\":" + HttpStatus.BAD_REQUEST.value() +
//                    ",\"message\":\"잘못된 파라미터입니다.\"}";
//            return errorMessage;
        }
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
        click_key = UUID.randomUUID().toString();

        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .path("/cookie-oven")
                .queryParam("click_key", click_key)
                .encode()
                .build()
                .toUri();

        return uri;
    }
}
