package com.example.demo.web;

import com.example.demo.service.PostService;
import com.example.demo.web.dto.PostListResponseDto;
import com.example.demo.web.dto.PostResponseDto;
import com.example.demo.web.dto.PostSaveRequestDto;
import com.example.demo.web.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save")
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

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) {
        postService.update(id, requestDto);

        return ResponseEntity.ok().body("변경 완료");
    }
}
