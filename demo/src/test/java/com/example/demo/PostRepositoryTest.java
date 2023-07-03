package com.example.demo;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostRepository;
import com.example.demo.dto.PostListResponseDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.PostSaveRequestDto;
import com.example.demo.dto.PostUpdateRequestDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PostRepositoryTest {

    PostRepository postRepository = new PostRepository();

    @Test
    void save() {
        //given
        PostSaveRequestDto post = PostSaveRequestDto.builder()
                .title("test")
                .author("tester")
                .content("test content")
                .build();

        //when
        Post savePost = postRepository.save(post);

        //then
        Post findPost = postRepository.findById(savePost.getId());
        assertThat(findPost.getId()).isEqualTo(savePost.getId());
    }

    @Test
    void findById() {
        //given
        PostSaveRequestDto post = PostSaveRequestDto.builder()
                .title("test")
                .author("tester")
                .content("test content")
                .build();
        Post savePost = postRepository.save(post);

        //when
        Post findPost = postRepository.findById(savePost.getId());
        PostResponseDto findOne = new PostResponseDto(findPost);

        //then
        assertThat(savePost.getId()).isEqualTo(findOne.getId());
    }

    @Test
    void findAll() {
        //given
        PostSaveRequestDto post = PostSaveRequestDto.builder()
                .title("test")
                .author("tester")
                .content("test content")
                .build();

        PostSaveRequestDto post2 = PostSaveRequestDto.builder()
                .title("test2")
                .author("tester2")
                .content("test content2")
                .build();

        postRepository.save(post);
        postRepository.save(post2);

        //when
        List<Post> posts = postRepository.findAll();
        List<PostListResponseDto> postList = new ArrayList<>();
        for (Post result : posts) {
            postList.add(new PostListResponseDto(result));
        }

//        List<PostListDto> result = postRepository.findAll().stream()
//                .map(posts -> new PostListDto(posts))
//                .collect(Collectors.toList());

        //then
        assertThat(postList.get(0).getTitle()).isEqualTo("test");
    }

    @Test
    void update() {
        //given
        PostSaveRequestDto post = PostSaveRequestDto.builder()
                .title("test")
                .author("tester")
                .content("test content")
                .build();
        Post savePost = postRepository.save(post);
        Long savePostId = savePost.getId();
        ;

        PostUpdateRequestDto updateDto = PostUpdateRequestDto.builder()
                .title("test2")
                .content("test content2")
                .build();

        //when
        Post updatePost = postRepository.update(savePost.getId(), updateDto);

        Post findPost = postRepository.findById(savePostId);

        //then
        assertThat(findPost.getId()).isEqualTo(updatePost.getId());
        assertThat(findPost.getTitle()).isEqualTo(updatePost.getTitle());
        assertThat(findPost.getContent()).isEqualTo(updatePost.getContent());
    }
}
