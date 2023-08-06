package poke.squad.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //전달 받은 click_key
    @GetMapping("/cookie-oven")
    public ResponseEntity<String> getData(HttpServletRequest request
                                          /*,@RequestParam("click_key") String clickKey*/) {
        //파라미터로 받은 click_key
        String clickKey = ServletRequestUtils.getStringParameter(request, "click_key", "");
        log.info("clickKey={}", clickKey);

        //key 저장
        String result = apiService.saveClickKey(clickKey);
        log.info("result={}", result);

        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/cookie-oven")
    public ResponseEntity<String> postData() {
        String key = apiService.findKey();
        log.info("key={}", key);
        String postResult = apiService.cookieOven(key);

        return ResponseEntity.ok().body(postResult);
    }
}
