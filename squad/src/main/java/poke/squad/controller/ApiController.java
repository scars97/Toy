package poke.squad.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import poke.squad.service.ApiService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/test")
    public ResponseEntity<String> postTest() {
        return apiService.restPostTest();
    }

    @GetMapping("/cookie-oven")
    public ResponseEntity<String> getData(HttpServletRequest request) {
        String clickKey = ServletRequestUtils.getStringParameter(request, "click_key", "");
        log.info("clickKey={}", clickKey);
        return ResponseEntity.ok().body(clickKey);
    }

    @PostMapping("/cookie-oven")
    public ResponseEntity<String> postData() {
        return apiService.cookieOven();
    }
}
