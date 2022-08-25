package com.innovation.team7_carrot_clone.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class SignupRequestDto {
    @NotNull
    private String userPhoneNum;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String passwordCheck;
}