package com.example.demo.domain;

import com.example.demo.web.dto.PostSaveRequestDto;
import com.example.demo.web.dto.PostUpdateRequestDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PostRepository {

    private static final Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;

    public Post save(PostSaveRequestDto saveDto) {
        Post post = Post.builder()
                .id(++sequence)
                .title(saveDto.getTitle())
                .author(saveDto.getAuthor())
                .content(saveDto.getContent())
                .build();
        store.put(post.getId(), post);
        return post;
    }

    public Post findById(Long id) {
        return store.get(id);
    }

    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    public Post update(Long id, PostUpdateRequestDto updateDto) {
        Post findPost = findById(id);

        findPost.update(updateDto.getTitle(), updateDto.getContent());

        return findPost;
    }

    public void delete(Long id) {
        store.remove(id);
    }
}
