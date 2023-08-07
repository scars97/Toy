package poke.squad.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ApiService {

    private static final Map<String, String> keyStore = new HashMap<>();
    private final String advertiserToken = "asdfqwefqwefasdfzxcvqsef123";

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

    /**
     * 토큰, 키 값 post - RestTemplate
     * @param clickKey
     * @return
     */
    public String postTokenAndKey(String clickKey) {

        URI uri = postUri(clickKey);

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

    public String httpPostTokenAndKey(String clickKey) throws IOException {
        CloseableHttpClient httpClient = null;
        URI uri = postUri(clickKey);

        try {
            httpClient = HttpClientBuilder.create().build();

            HttpPost post = new HttpPost(uri);

            CloseableHttpResponse httpResponse = httpClient.execute(post);

            return EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e){
            log.info("http 통신 오류");
        } finally {
            httpClient.close();
        }
        return null;
    }

    private URI postUri(String clickKey) {
        String apiUrl = "http://localhost:9090";

        URI uri = UriComponentsBuilder
                .fromUriString(apiUrl)
                .path("/cookieoven-test")
                .queryParam("advertiser_token", advertiserToken)
                .queryParam("click_key", clickKey)
                .encode()
                .build()
                .toUri();

        return uri;
    }
}
