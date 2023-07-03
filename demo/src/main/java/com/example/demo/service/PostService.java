package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostRepository;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public String save(PostSaveRequestDto requestDto) {
        postRepository.save(requestDto);
        return "저장 완료";
    }

    public PostResponseDto findById(Long id) {

        Post entity = postRepository.findById(id);
        if(entity == null){
            new IllegalArgumentException("해당 게시글 없음");
        }

        return new PostResponseDto(entity);
    }
}
