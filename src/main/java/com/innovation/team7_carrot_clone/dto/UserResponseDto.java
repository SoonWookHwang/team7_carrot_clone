package com.innovation.team7_carrot_clone.dto;

import com.innovation.team7_carrot_clone.model.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private String imageUrl;
    private final LocalDateTime modifiedAt;

//    @Builder
//    public UserResponseDto(User user, String imageUrl){
//        this.imageUrl = imageUrl;
//        this.modifiedAt = user.getModifiedAt();
//    }

    @Builder
    public UserResponseDto(User user, String imageUrl){
        this.imageUrl = imageUrl;
        this.modifiedAt = user.getModifiedAt();
    }
}
