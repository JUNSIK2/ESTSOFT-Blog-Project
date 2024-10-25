package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.entity.Article;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddArticleRequest {
//    @JsonProperty(value = "id")
//    private Long articleId;
//    @JsonProperty(value = "title")
    private String title;
//    @JsonProperty(value = "body")
    private String content;

    public Article toEntity() {
        return new Article(title, content);
    }
}
