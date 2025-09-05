package com.slightly_techie.blog.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String author;
    private boolean published;
    private String tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
