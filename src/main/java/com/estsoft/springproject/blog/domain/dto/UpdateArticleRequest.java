package com.estsoft.springproject.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateArticleRequest {
    private String title;
    private String content;
}
