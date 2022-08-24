package com.innovation.team7_carrot_clone.controller;

import com.innovation.team7_carrot_clone.dto.LoginRequestDto;
import com.innovation.team7_carrot_clone.dto.SignupRequestDto;
import com.innovation.team7_carrot_clone.dto.UserResponseDto;
import com.innovation.team7_carrot_clone.jwt.JwtTokenProvider;
import com.innovation.team7_carrot_clone.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginRequestDto requestDto){
        ResponseEntity<UserResponseDto> userResponse;
        try{
            userResponse = new ResponseEntity<>(userService.login(requestDto),HttpStatus.OK);
        }catch(IllegalAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
            return userResponse;
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