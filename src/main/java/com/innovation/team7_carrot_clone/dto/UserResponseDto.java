package com.innovation.team7_carrot_clone.dto;

import com.innovation.team7_carrot_clone.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private final String username;
    private String token;

    @Builder
    public UserResponseDto(User user, String token){
        this.username = user.getUsername();
        this.token = token;
    }
}
