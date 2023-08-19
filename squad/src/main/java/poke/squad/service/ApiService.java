package poke.squad.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import poke.squad.exception.CustomResponseErrorHandler;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ApiService {

    private static final Map<String, String> keyStore = new HashMap<>();
    private final String advertiserToken = "asdfqwefqwefasdfzxcvqsef123";
    private final RestTemplate restTemplate;
    public ApiService() {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(new CustomResponseErrorHandler());
    }

    public ResponseEntity<String> restPostTest() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/rest-test")
                .encode()
                .build()
                .toUri();

        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("title", "Test Title");
        bodyMap.put("author", "Test Author");
        bodyMap.put("content", "Test Content");

        RequestEntity<Map<String, String>> request = RequestEntity
                .post(uri)
                .body(bodyMap);

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return response;
    }

    //click_key 저장
    public String saveKey(String clickKey) {
        if (clickKey != null) {
            keyStore.put("key", clickKey);
        } else {
            throw new IllegalArgumentException("값이 없습니다.");
        }

        return clickKey;
    }

    //click_key 값 찾기
    public String findKey() {
        return keyStore.get("key");
    }

    /**
     * 토큰, 키 값 post - RestTemplate
     * @param clickKey
     * @return
     */
    public Map<String, Object> postTokenAndKey(String clickKey) {
        URI uri = postUri(clickKey);

        //body
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("advertiser_token", advertiserToken);
        requestMap.put("click_key", clickKey);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, requestMap, String.class);

        int status = response.getStatusCodeValue();
        log.info("status={}", status);
        String result = response.getBody();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", status);
        resultMap.put("result", result);
        return resultMap;
    }

    public Map<String, Object> httpPostTokenAndKey(String clickKey) {
        Map<String, Object> resultMap = new HashMap<>();

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            URI uri = postUri(clickKey);
            HttpPost post = new HttpPost(uri);

            try (CloseableHttpResponse httpResponse = httpClient.execute(post)) {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                String result = EntityUtils.toString(httpResponse.getEntity());

                resultMap.put("status", statusCode);
                resultMap.put("result", result);
            }

        } catch (IOException e) {
            log.error("HTTP 통신 오류", e);
            resultMap.put("error", "HTTP 통신 오류");
        }

        return resultMap;
    }

    private URI postUri(String clickKey) {
        String apiUrl = "http://localhost:9090";

        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .path("/return-data")
                .queryParam("advertiser_token", advertiserToken)
                .queryParam("click_key", clickKey)
                .encode()
                .build()
                .toUri();

        return uri;
    }
}
