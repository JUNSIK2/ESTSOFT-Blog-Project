package com.estsoft.springproject.blog.domain.dto;

import com.estsoft.springproject.blog.domain.entity.Article;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "블로그 조회 결과")
public class ArticleResponse {
    @Schema(description = "블로그 ID", type = "Long")
    private Long articleId;
    @Schema(description = "블로그 제목", type = "String")
    private String title;
    @Schema(description = "블로그 내용", type = "String")
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;

    @Builder
    public ArticleResponse(Long article_id, String title, String content) {
        this.articleId = article_id;
        this.title = title;
        this.content = content;
    }

    public ArticleResponse(Article article) {
        this.articleId = article.getArticleId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
    }
}
