package poke.squad.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class ApiService {

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

    public ResponseEntity<String> cookieOven() {

        String API_POST_URL = "http://localhost:9090";
        String advertiserToken = "asdfqwefqwefasdfzxcvqsef123";
        String clickKey = "wdfqwefqweuifh123";

        URI uri = UriComponentsBuilder
                .fromUriString(API_POST_URL)
                .path("/post/cookieoven-test")
                .encode()
                .build()
                .toUri();

        String body = "{\"advertiser_token\":\"" + advertiserToken +
                "\",\"click_key\":\"" + clickKey + "\"}";

        RequestEntity<String> request = RequestEntity
                .post(uri)
                .body(body);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        HttpStatus status = response.getStatusCode();
        log.info("status={}", status.value());
        if(status == HttpStatus.OK){
            log.info("Post Success");
        }else{
            log.info("Post Fail");
        }

        return response;
    }
}
