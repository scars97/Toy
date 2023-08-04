package poke.squad.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import poke.squad.service.ApiService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/test")
    public ResponseEntity<String> postTest() {
        return apiService.restPostTest();
    }

    @PostMapping("/cookie-oven")
    public ResponseEntity<String> postToken() {
        return apiService.cookieOven();
    }
}
