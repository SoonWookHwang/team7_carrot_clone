package com.innovation.team7_carrot_clone.controller;

import com.innovation.team7_carrot_clone.dto.LoginRequestDto;
import com.innovation.team7_carrot_clone.dto.SignupRequestDto;
import com.innovation.team7_carrot_clone.jwt.JwtTokenProvider;
import com.innovation.team7_carrot_clone.service.UserService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public String doLogin(@RequestBody LoginRequestDto requestDto){
        if(userService.login(requestDto)) {
            String token = this.jwtTokenProvider.createToken(requestDto.getUserPhoneNum());
            System.out.println(token);
            return token;
        }
        else return "아이디, 비밀번호를 확인해주세요.";
    }

    @PostMapping("/signup")
    public String doSignup(@RequestBody SignupRequestDto requestDto){
        return userService.registerUser(requestDto);
    }

    public UserController (UserService userService, JwtTokenProvider jwtTokenProvider){
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

}