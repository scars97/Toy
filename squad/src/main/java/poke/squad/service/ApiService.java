package poke.squad.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ApiService {

    private static final Map<String, String> keyStore = new HashMap<>();

    public ResponseEntity<String> restPostTest() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/post/rest-test")
                /*
                .queryParam("title", "Test Title")
                .queryParam("author", "Test Author")
                .queryParam("content", "Test Content")
                */
                .encode()
                .build()
                .toUri();

        String testResult = "{\"title\":\"Test Title\",\"author\":\"Test Author\",\"content\":\"Test Content\"}";

        RequestEntity<String> request = RequestEntity
                .post(uri)
                .body(testResult);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return response;
    }

    //click_key 저장
    public String saveKey(String clickKey) {
        keyStore.put("key", clickKey);
        return "저장 완료";
    }

    //click_key 값 찾기
    public String findKey() {
        return keyStore.get("key");
    }

    public String postTokenAndKey(String clickKey) {

        String API_POST_URL = "http://localhost:9090";
        String advertiserToken = "asdfqwefqwefasdfzxcvqsef123";

        URI uri = UriComponentsBuilder
                .fromUriString(API_POST_URL)
                .path("/cookieoven-test")
                .queryParam("advertiser_token", advertiserToken)
                .queryParam("click_key", clickKey)
                .encode()
                .build()
                .toUri();

        String body = "{\"advertiser_token\":\"" + advertiserToken +
                "\",\"click_key\":\"" + clickKey + "\"}";

        RestTemplate restTemplate = new RestTemplate();
        //쿼리파라미터, 바디 값 전송
        ResponseEntity<String> response = restTemplate.postForEntity(uri, body, String.class);

        HttpStatus status = response.getStatusCode();
        log.info("status={}", status.value());
        if(status == HttpStatus.OK){
            log.info("Post Success");
        }else{
            log.info("Post Fail");
        }

        //token,key 전송 후 리턴받는 값
        return response.getBody();
    }
}
