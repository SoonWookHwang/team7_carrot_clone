package com.innovation.team7_carrot_clone.dto;

import com.innovation.team7_carrot_clone.model.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostRequestDto {
    private String title;
    private String contents;
    private String category;
    private String price;
}
