package com.example.demo.web.controller;

import com.example.demo.service.PostService;
import com.example.demo.web.dto.postdto.PostListResponseDto;
import com.example.demo.web.dto.postdto.PostResponseDto;
import com.example.demo.web.dto.postdto.PostSaveRequestDto;
import com.example.demo.web.dto.postdto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
