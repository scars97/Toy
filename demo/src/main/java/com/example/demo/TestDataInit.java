package com.example.demo;

import com.example.demo.domain.post.PostRepository;
import com.example.demo.web.dto.postdto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final PostRepository postRepository;

    @PostConstruct
    public void init(){
        PostSaveRequestDto dto = PostSaveRequestDto.builder()
                .title("test")
                .author("tester")
                .content("test content")
                .build();
        PostSaveRequestDto dto2 = PostSaveRequestDto.builder()
                .title("test2")
                .author("tester2")
                .content("test content2")
                .build();

        log.info("dataInit : {}", dto);
        log.info("dataInit : {}", dto2);

        postRepository.save(dto);
        postRepository.save(dto2);
    }

}
