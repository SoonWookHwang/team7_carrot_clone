package com.innovation.team7_carrot_clone.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.innovation.team7_carrot_clone.model.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long post_id;
    private String title;
    private String contents;
    private String category;
    private String price;
    private String imgURL;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;


    @Builder
    public PostResponseDto(Post post){
        this.post_id = post.getPost_id();
        this.title = post.getTitle();
        this.contents = post.getCategory();
        this.category = post.getCategory();
        this.price = post.getPrice();
        this.imgURL = post.getImgURL();
        this.modifiedAt = post.getModifiedAt();
    }

}
