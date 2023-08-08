package com.example.demo.web.controller;

import com.example.demo.service.ApiService;
import com.example.demo.domain.api.ApiDataDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/rest-test")
    public ResponseEntity<String> restSave(@RequestBody String result
                                           /*
                                           @RequestParam String title,
                                           @RequestParam String author,
                                           @RequestParam String content*/) {
/*
        log.info("title={}", title);
        log.info("author={}", author);
        log.info("content={}", content);
*/
        log.info("result={}", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //click_key 전달
    @GetMapping("/send-data")
    public ResponseEntity<String> sendKey() throws IOException {
        //String sendResult = apiService.sendData();
        String sendResult = apiService.httpSendData();
        log.info("sendResult={}", sendResult);
        return ResponseEntity.ok().body(sendResult);
    }

    //token, key 처리
    @PostMapping("/cookieoven-test")
    public ResponseEntity<String> processTokenAndKey(/*@RequestBody String result,*/
                                 @RequestParam("advertiser_token") String token,
                                 @RequestParam("click_key") String clickKey,
                                 HttpServletRequest request) throws JsonProcessingException {
        String clientIp = request.getRemoteAddr();
        log.info("clientIp={}", clientIp);
        //log.info("result={}", result);
        log.info("token={}", token);
        log.info("clickKey={}", clickKey);

        String resultData = apiService.saveTokenAndKey(token, clickKey);
        log.info("resultData={}", resultData);

        return ResponseEntity.ok().body(resultData);
    }

    //전달받은 click_key 값 find
    @GetMapping("/cookieoven-test/{id}")
    public ResponseEntity<ApiDataDto> findTokenAndKey(@PathVariable Long id) {
        ApiDataDto resultData = apiService.findById(id);

        log.info("resultData={}", resultData);

        return ResponseEntity.ok().body(resultData);
    }
}
