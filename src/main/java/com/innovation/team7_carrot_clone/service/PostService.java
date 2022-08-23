package com.innovation.team7_carrot_clone.service;

import com.innovation.team7_carrot_clone.dto.PostRequestDto;
import com.innovation.team7_carrot_clone.dto.PostResponseDto;
import com.innovation.team7_carrot_clone.model.Post;
import com.innovation.team7_carrot_clone.model.User;
import com.innovation.team7_carrot_clone.repository.PostRepository;
import com.innovation.team7_carrot_clone.repository.UserRepository;
import com.innovation.team7_carrot_clone.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // Post 리스트 조회 - responseDto의 @Builder와 연계됨.
    public List<PostResponseDto> getPostList(){
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postList = new ArrayList<>();

        for(Post post : posts){
            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .post(post)
                    .build();
            postList.add(postResponseDto);
        }

        return postList;
    }

    // Post 상세 조회
    public PostResponseDto getPost(Long post_id){
        Post post = postRepository.findById(post_id).orElseThrow(
                () -> new IllegalArgumentException("Couldn't find the post")
        );
        return PostResponseDto.builder()
                .post(post)
                .build();

    }

    // Post 생성
    @Transactional
    public Post createPost(PostRequestDto requestDto, Long userId, String username){
        User userFoundById = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        Post postCreated;

        if(userFoundById.getUsername().equals(username)){
            Post post = requestDto.createPost();
            post.mapToUser(userFoundById);
            return postRepository.save(post);
        }
        return null;
    }

    // Post 수정
    @Transactional
    public Post updatePost(Long post_id, PostRequestDto postRequestDto, UserDetailsImpl userDetails){
        Post post = postRepository.findById(post_id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        String loginUser = userDetails.getUser().getUsername();
        String author = post.getUser().getUsername();
        if(author.equals(loginUser)){
            post.update(postRequestDto.getTitle(), postRequestDto.getContents(), postRequestDto.getCategory(), postRequestDto.getPrice(), postRequestDto.getImageUrl());
        }else {
            throw new IllegalArgumentException("해당 게시글에 대한 수정 권한이 없습니다.");
        }
        return post;
    }

    // Post 삭제
    public void deletePost(Long post_id,UserDetailsImpl userDetails) throws IllegalArgumentException{
        Post post = postRepository.findById(post_id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        String loginUser = userDetails.getUser().getUsername();
        String author = post.getUser().getUsername();
        if(author.equals(loginUser)){
            postRepository.deleteById(post_id);
        }else {
            throw new IllegalArgumentException("해당 게시글에 대한 삭제 권한이 없습니다.");
        }
    }
}
