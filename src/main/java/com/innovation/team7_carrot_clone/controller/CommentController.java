//package com.innovation.team7_carrot_clone.controller;
//
//import com.innovation.team7_carrot_clone.model.User;
//import com.innovation.team7_carrot_clone.security.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//
//@RequestMapping("/api/comments")
//@RequiredArgsConstructor
//@RestController
//public class CommentController {
//    private final CommentService commentService;
//
//    @GetMapping("/{contentId}")
//    public ResponseDto<?> getComment(@PathVariable Long contentId) {
//        try {
//            return ResponseDto.success(commentService.getComment(contentId));
//        } catch (IllegalArgumentException e) {
//            return ResponseDto.fail("", e.getMessage());
//        }
//    }
//    @PostMapping
//    public ResponseEntity<String> createComment(@RequestBody HashMap<String, Object> data,
//                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        try {
//            User user = userDetails.getUser();
//            commentService.createComment(data, user);
//        } catch (NullPointerException n) {
//            return new ResponseEntity<>("로그인 후 이용해주세요", HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>("댓글이 등록되었습니다", HttpStatus.OK);
//    }
//    @DeleteMapping("/{commentId}")
//    public ResponseEntity<String> deleteComment(@PathVariable Long commentId,
//                                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        try {
//            User user = userDetails.getUser();
//            commentService.deleteComment(commentId, user);
//        } catch (NullPointerException n) {
//            return new ResponseEntity<>("로그인 후 이용해주세요", HttpStatus.OK);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>("댓글이 정상적으로 삭제되었습니다", HttpStatus.OK);
//    }
//}
