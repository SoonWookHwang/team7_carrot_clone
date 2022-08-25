package com.innovation.team7_carrot_clone.controller;

import com.innovation.team7_carrot_clone.model.User;
import com.innovation.team7_carrot_clone.repository.PostRepository;
import com.innovation.team7_carrot_clone.repository.UserRepository;
import com.innovation.team7_carrot_clone.config.UserDetailsImpl;
import com.innovation.team7_carrot_clone.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@Transactional
@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Service s3Service;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 마이페이지에서 유저 정보 수정 시 유저의 사진 업로드 또는 수정 가능.
    @PostMapping("/users/{userId}/image")
    public String S3UserImageUpload(@RequestPart MultipartFile file,
                                    @PathVariable (name = "userId") Long userId,
                                    @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) throws IOException {
        String username = userDetailsImpl.getUsername();
        User userFound = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지 않습니다."));

        if(userDetailsImpl.getUser().getId().equals(userId)){
            String userImageURL = this.s3Service.S3UserImageUpload(file, userId, username).getImageUrl();
            userFound.mapToUser(userImageURL);
            return "redirect:/users/mypage";
        }
        return "login";
    }
}
