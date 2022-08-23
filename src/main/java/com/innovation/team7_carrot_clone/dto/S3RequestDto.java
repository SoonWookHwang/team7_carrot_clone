package com.innovation.team7_carrot_clone.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class S3RequestDto {
    private String imageUrl;
}
