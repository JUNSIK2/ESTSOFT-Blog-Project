package com.estsoft.springproject.blog.domain.entity;

import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Builder
    public Article(Long articleId , String title, String content) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
    }

    public ArticleResponse toArticleResponse(){
        return ArticleResponse.builder()
                .article_id(this.articleId)
                .title(this.title)
                .content(this.content)
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}