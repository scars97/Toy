package com.example.demo.service;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostRepository;
import com.example.demo.web.dto.postdto.PostListResponseDto;
import com.example.demo.web.dto.postdto.PostResponseDto;
import com.example.demo.web.dto.postdto.PostSaveRequestDto;
import com.example.demo.web.dto.postdto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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
        if (entity == null) {
            throw new IllegalArgumentException("해당 게시글 없음");
        }

        return new PostResponseDto(entity);
    }

    public List<PostListResponseDto> findAll() {
        return postRepository.findAll().stream()
                .map(posts -> new PostListResponseDto(posts))
                .collect(Collectors.toList());

/*        List<PostListResponseDto> postList = new ArrayList<>();
        for (Post result : postRepository.findAll()) {
            postList.add(new PostListResponseDto(result));
        }
        return postList;*/
    }

    public String update(Long id, PostUpdateRequestDto requestDto) {
        postRepository.update(id, requestDto);

        return "수정 완료";
    }

    public String delete(Long id) {
        postRepository.delete(id);
        return "삭제 완료";
    }
}
