package com.innovation.team7_carrot_clone.controller;

import com.innovation.team7_carrot_clone.dto.PostResponseDto;
import com.innovation.team7_carrot_clone.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/api/posts")
    public void createPost(){
        postService.createPost();
    }

    // 게시글 전체 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPostList(){
        return postService.getPostList();
    }
}
