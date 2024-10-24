package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.entity.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private Long commentId;
    private String body;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private ArticleResponse articleResponse;


    public CommentResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();
        this.articleResponse = new ArticleResponse(comment.getArticle());
    }


}
