//package com.innovation.team7_carrot_clone.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.innovation.team7_carrot_clone.model.Comment;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//public class CommentResponseDto {
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private final LocalDateTime modifiedAt;
//    private Long commentId;
//    private String username;
//    private String commentContent;
//    private int countLike;
//    private List<CommentResponseDto> replies;
//
//    @Builder
//    public CommentResponseDto(Comment comment, int countLike) {
//        this.modifiedAt = comment.getModifiedAt();
//        this.commentId = comment.getCommentId();
//        this.username = comment.getUser().getUsername();
//        this.commentContent = comment.getCommentContent();
//        this.countLike = countLike;
//        this.replies = new ArrayList<>();
//    }
//}