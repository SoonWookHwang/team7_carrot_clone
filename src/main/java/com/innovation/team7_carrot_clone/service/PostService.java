package com.innovation.team7_carrot_clone.service;

import com.innovation.team7_carrot_clone.dto.PostRequestDto;
import com.innovation.team7_carrot_clone.dto.PostResponseDto;
import com.innovation.team7_carrot_clone.model.Post;
import com.innovation.team7_carrot_clone.model.User;
import com.innovation.team7_carrot_clone.repository.PostRepository;
import com.innovation.team7_carrot_clone.repository.UserRepository;
import com.innovation.team7_carrot_clone.config.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    // Post 리스트 조회 - responseDto의 @Builder와 연계됨.
    public List<PostResponseDto> getPostList() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postList = new ArrayList<>();

        for (Post post : posts) {
            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .post(post)
                    .build();
            postList.add(postResponseDto);
        }

        return postList;
    }

    // Post 상세 조회
    public PostResponseDto getPost(Long post_id) {
        Post post = postRepository.findById(post_id).orElseThrow(
                () -> new IllegalArgumentException("Couldn't find the post")
        );
        return PostResponseDto.builder()
                .post(post)
                .build();
    }

    // Post 생성
    @Transactional
    public Post createPost(PostRequestDto requestDto, UserDetailsImpl userDetails, MultipartFile file, Long userId) {
        User userFoundById = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        String imageUrl = "";
        if (userRepository.findById(userDetails.getUser().getId()).isPresent()) {
            if (!file.isEmpty()) {
                imageUrl = s3Service.uploadFile(file);
            }
//            Post post = new Post(requestDto.getTitle(), requestDto.getContents(), requestDto.getCategory(), requestDto.getPrice(), imageUrl);
//            Post post = Post.builder()
//                    .title(requestDto.getTitle())
//                    .contents(requestDto.getContents())
//                    .category(requestDto.getCategory()).price(requestDto.getPrice()).imageUrl(imageUrl).build();
//            post.mapToUser(userDetails.getUser());
//            postRepository.save(post);
//            return post;
        }
        Post post = Post.builder()
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .category(requestDto.getCategory()).price(requestDto.getPrice()).imageUrl(imageUrl).build();
        post.mapToUser(userFoundById);
        postRepository.save(post);
        return post;
    }

    // Post 수정
    @Transactional
    public PostResponseDto updatePost(Long post_id, PostRequestDto postRequestDto, UserDetailsImpl userDetails, MultipartFile file, Long userId) {
//        Post post = postRepository.findById(post_id).orElseThrow(
//                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
//        );
//        String loginUser = userDetails.getUser().getUsername();
//        String author = post.getUser().getUsername();
//        String imageUrl = post.getImageUrl();
//        if (author.equals(loginUser)) {
//            post.update(postRequestDto.getTitle(), postRequestDto.getContents(), postRequestDto.getCategory(), postRequestDto.getPrice(), imageUrl);
//        } else {
//            throw new IllegalArgumentException("해당 게시글에 대한 수정 권한이 없습니다.");
//        }
//        return PostResponseDto.builder()
//                .post(post)
//                .build();
        User userFoundById = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));
        Post postFoundById = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        String imageUrl = "";
        if (userRepository.findById(userDetails.getUser().getId()).isPresent()) {
            if (!file.isEmpty()) {
                imageUrl = s3Service.uploadFile(file);
            }
        }
        postFoundById.update(postRequestDto.getTitle(), postRequestDto.getContents(), postRequestDto.getCategory(), postRequestDto.getPrice(), imageUrl);
        postFoundById.mapToUser(userFoundById);
        postRepository.save(postFoundById);
        return PostResponseDto.builder().post(postFoundById).build();
    }

    // Post 삭제
    public void deletePost(Long post_id, UserDetailsImpl userDetails) throws IllegalArgumentException {
        Post post = postRepository.findById(post_id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
        );
        String loginUser = userDetails.getUser().getUsername();
        String author = post.getUser().getUsername();
        if (author.equals(loginUser)) {
            postRepository.deleteById(post_id);
        } else {
            throw new IllegalArgumentException("해당 게시글에 대한 삭제 권한이 없습니다.");
        }
    }
}
