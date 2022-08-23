package com.innovation.team7_carrot_clone.service;

import com.innovation.team7_carrot_clone.dto.LoginRequestDto;
import com.innovation.team7_carrot_clone.dto.SignupRequestDto;
import com.innovation.team7_carrot_clone.jwt.JwtTokenProvider;
import com.innovation.team7_carrot_clone.model.User;
import com.innovation.team7_carrot_clone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public Boolean login(LoginRequestDto loginRequestDto) {
        User user = this.userRepository.findByUserPhoneNum(loginRequestDto.getUserPhoneNum()).orElse((User) null);
        if(user != null) {
            return this.passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());
        }
        else    return false;
    }

    public String registerUser(SignupRequestDto requestDto) {
        String userPhoneNum = requestDto.getUserPhoneNum();
        String userName = requestDto.getUserName();
        String password = requestDto.getPassword();
        String passwordCheck = requestDto.getPasswordCheck();

        Optional<User> numToCheck = this.userRepository.findByUserPhoneNum(userPhoneNum);
        Optional<User> nameToCheck = this.userRepository.findByUserName(userName);

        if(numToCheck.isPresent()){
            return "중복된 번호가 존재합니다.";
        } else if(!Objects.equals(password, passwordCheck)){
            return "비밀번호가 일치하지 않습니다.";
        } else if(Objects.equals(userPhoneNum, "")){
            return "핸드폰 번호 형식의 아이디를 입력해주세요.";
        } else if(Objects.equals(password, "")) {
            return "비밀번호를 입력해주세요.";
        } else if(nameToCheck.isPresent()){
            return "중복된 이름이 존재합니다.";
        } else{
            password = this.passwordEncoder.encode(password);
            requestDto.setPassword(password);
            User user = new User(userPhoneNum, userName, password);
            this.userRepository.save(user);
            return "회원가입에 성공하였습니다.";
        }
    }

}
