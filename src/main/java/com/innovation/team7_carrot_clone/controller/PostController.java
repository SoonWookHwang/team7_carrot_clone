package com.innovation.team7_carrot_clone.controller;

import com.innovation.team7_carrot_clone.dto.PostRequestDto;
import com.innovation.team7_carrot_clone.dto.PostResponseDto;
import com.innovation.team7_carrot_clone.dto.UserResponseDto;
import com.innovation.team7_carrot_clone.model.Post;
import com.innovation.team7_carrot_clone.security.UserDetailsImpl;
import com.innovation.team7_carrot_clone.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/posts")
    public String createPost(@RequestBody PostRequestDto postRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetailsImpl){
        if(userDetailsImpl.getUser() != null){
            Long userId = userDetailsImpl.getUser().getId();
            String username = userDetailsImpl.getUsername();

            this.postService.createPost(postRequestDto, userId, username);
            return "redirect:/posts";
        }
        return "login";
    }

    // 게시글 전체 조회
    @GetMapping("/posts")
    public List<PostResponseDto> getPostList(){
        return postService.getPostList();
    }

    // 게시물 상세 조회
    @ResponseBody
    @GetMapping("/posts/{post_id}")
    public PostResponseDto getPost(@PathVariable Long post_id){
        return postService.getPost(post_id);
    }

    // 게시물 수정
    @PutMapping("/posts/{post_id}")
    public Post updatePost(@PathVariable Long post_id, PostRequestDto postRequestDto){
        return postService.updatePost(post_id, postRequestDto);
    }

    // 게시물 삭제
    @DeleteMapping("/posts/{post_id}")
    public Long deletePost(@PathVariable Long post_id){
        return postService.deletePost(post_id);
    }
}
