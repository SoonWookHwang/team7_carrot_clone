package com.innovation.team7_carrot_clone.controller;

import com.innovation.team7_carrot_clone.dto.PostRequestDto;
import com.innovation.team7_carrot_clone.dto.PostResponseDto;
import com.innovation.team7_carrot_clone.repository.PostRepository;
import com.innovation.team7_carrot_clone.config.UserDetailsImpl;
import com.innovation.team7_carrot_clone.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
//@RequestMapping(value = "/posts", headers = ("content-type=multipart/*"))
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    // 게시글 작성
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}) //put에 추가
    public String createPost(@RequestPart PostRequestDto postRequestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                             @RequestPart(required = false) MultipartFile file){
        if(userDetailsImpl.getUser() != null){
            Long userId = userDetailsImpl.getUser().getId();
            String username = userDetailsImpl.getUsername();
            this.postService.createPost(postRequestDto,userDetailsImpl,file, userId);
            return "redirect:/posts";
        }
        return "login";
    }

    // 게시글 전체 조회
    @GetMapping
    public List<PostResponseDto> getPostList(){
        return postService.getPostList();
    }

    // 게시물 상세 조회
    @ResponseBody
    @GetMapping("/{post_id}")
    public PostResponseDto getPost(@PathVariable Long post_id){
        return postService.getPost(post_id);
    }

    // 게시물 수정
//    @ResponseBody
    @PutMapping(value = "/{post_id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}, headers = ("content-type=multipart/*"))
    public String updatePost(@PathVariable(name = "post_id") Long post_id,
                             @RequestPart PostRequestDto postRequestDto,
                             @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
                             @RequestPart(required = false) MultipartFile file) {
//        try{
//            postService.updatePost(post_id, postRequestDto, userDetailsImpl, file);
//        }catch (IllegalArgumentException e){
//            log.info(e.getMessage());
//            return "login";
//        }
//        return "redirect:/posts";
        if(userDetailsImpl.getUser() != null){
            Long userId = userDetailsImpl.getUser().getId();
            this.postService.updatePost(post_id, postRequestDto,userDetailsImpl, file, userId);
            return "redirect:/posts";
        }
        return "login";
    }

    // 게시물 삭제
    @DeleteMapping("/{post_id}")

    public String deletePost(@PathVariable Long post_id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        try{
            postService.deletePost(post_id,userDetails);
        }catch (IllegalArgumentException e){
            log.info(e.getMessage());
        }
        return "redirect:/posts";
    }
}
