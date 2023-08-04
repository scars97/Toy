package com.example.demo.web;

import com.example.demo.service.PostService;
import com.example.demo.web.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long id) {
        PostResponseDto dto = postService.findById(id);

        log.info("post: {}", dto);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody PostSaveRequestDto requestDto) {
        String savePost = postService.save(requestDto);

        log.info("savePost: {}", requestDto);

        return ResponseEntity.ok().body(savePost);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostListResponseDto>> findAll() {
        List<PostListResponseDto> dto = postService.findAll();

        log.info("all : {}", dto);

        return ResponseEntity.ok().body(dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        String updatePost = postService.update(id, requestDto);

        log.info("update : {}", requestDto);

        return ResponseEntity.ok().body(updatePost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        String deletePost = postService.delete(id);

        return ResponseEntity.ok().body(deletePost);
    }

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

        String resultData = postService.apiDataSave(result);

        return resultData;
    }

    @GetMapping("/cookieoven-test/{id}")
    public ApiDataDto findByApiData(@PathVariable Long id) {
        ApiDataDto resultData = postService.findByApiData(id);

        log.info("resultData={}", resultData);

        return resultData;
    }
}
