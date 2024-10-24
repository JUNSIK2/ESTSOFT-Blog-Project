package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.entity.Article;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class ArticleCommentResponse {
    private Long articleId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<CommentResponse> commentResponseList;

    public ArticleCommentResponse(ArticleResponse article, List<CommentResponse> commentResponseList) {
        this.articleId = article.getArticleId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
        this.commentResponseList = commentResponseList;
    }
}
