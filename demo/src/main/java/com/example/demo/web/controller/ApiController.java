package com.example.demo.web.controller;

import com.example.demo.service.ApiService;
import com.example.demo.domain.api.ApiDataDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
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

    @PostMapping("/cookieoven-test")
    public String cookieOvenTest(@RequestBody String result, HttpServletRequest request) throws JsonProcessingException {
        String clientIp = request.getRemoteAddr();
        log.info("clientIp={}", clientIp);
        log.info("result={}", result);

        String resultData = apiService.apiDataSave(result);

        return resultData;
    }

    @GetMapping("/cookieoven-test/{id}")
    public ApiDataDto findByApiData(@PathVariable Long id) {
        ApiDataDto resultData = apiService.findByApiData(id);

        log.info("resultData={}", resultData);

        return resultData;
    }

    @GetMapping("/send-data")
    public ResponseEntity<String> sendData(){
        String sendResult = apiService.sendData();
        log.info("sendResult={}", sendResult);
        return ResponseEntity.ok().body(sendResult);
    }
}
