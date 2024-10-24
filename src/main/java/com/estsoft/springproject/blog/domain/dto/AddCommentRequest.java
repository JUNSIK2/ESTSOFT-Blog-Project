package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.entity.Article;
import com.estsoft.springproject.blog.domain.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AddCommentRequest {
    private Article article;
    @JsonProperty(value = "body")
    private String body;

    public Comment toEntity(){
        return Comment.builder()
                .article(this.article)
                .body(this.body)
                .build();
    }
}
