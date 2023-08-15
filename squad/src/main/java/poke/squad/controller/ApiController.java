package poke.squad.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import poke.squad.service.ApiService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApiController {

    private final ApiService apiService;

    @PostMapping("/test")
    public ResponseEntity<String> postTest() {
        return apiService.restPostTest();
    }

    //전달 받은 click_key
    @GetMapping("/get-key")
    public ResponseEntity<String> getKey(/*HttpServletRequest request,*/
                                          @RequestParam("click_key") String clickKey) {
        //파라미터로 받은 click_key
        //String clickKey = ServletRequestUtils.getStringParameter(request, "click_key", "");
        log.info("clickKey={}", clickKey);

        //key 저장
        String result = apiService.saveKey(clickKey);
        log.info("result={}", result);

        return ResponseEntity.ok().body(result + " 저장 완료");
    }

    @PostMapping("/cookie-oven")
    public ResponseEntity<String> postTokenAndKey() throws IOException {
        String key = apiService.findKey();
        log.info("key={}", key);

        Map<String, Object> postResult = apiService.postTokenAndKey(key);
        //Map<String, Object> postResult = apiService.httpPostTokenAndKey(key);
        int status = (int) postResult.get("status");
        String result = (String) postResult.get("result");

        return ResponseEntity.status(status).body(result);
    }
}
