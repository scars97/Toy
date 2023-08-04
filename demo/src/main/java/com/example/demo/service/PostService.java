package com.example.demo.service;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostRepository;
import com.example.demo.web.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    //api 연동 예제 데이터 저장
    public String apiDataSave(String result) throws JsonProcessingException {
        //Map<String, Object> apiData = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> apiData = mapper.readValue(result, HashMap.class);

        ApiDataDto apiDataDto = postRepository.apiSave(apiData);
        if (apiDataDto == null) {
            throw new IllegalArgumentException("저장 오류");
        }
        return "{}";
    }

    public ApiDataDto findByApiData(Long id) {
        ApiDataDto findOne = postRepository.findByApiId(id);
        if (findOne == null) {
            throw new IllegalArgumentException("해당 api 데이터 없음");
        }
        return findOne;
    }
}
