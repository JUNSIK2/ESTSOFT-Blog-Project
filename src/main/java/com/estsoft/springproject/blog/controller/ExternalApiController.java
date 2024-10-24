package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.dto.AddCommentRequest;
import com.estsoft.springproject.blog.domain.entity.Article;
import com.estsoft.springproject.blog.domain.entity.Comment;
import com.estsoft.springproject.blog.service.BlogService;
import com.estsoft.springproject.blog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class ExternalApiController {
    private final BlogService blogService;
    private final CommentService commentService;

    @Autowired
    public ExternalApiController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @GetMapping("/api/external/article")
    public String saveExternalDataArticle() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        ResponseEntity<List<AddArticleRequest>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<AddArticleRequest>>() {
                });

        if (response.getBody() == null || response.getBody().isEmpty()) {
            throw new NullPointerException();
        } else {
            List<Article> articleList = blogService.saveArticleList(response.getBody());
            return "OK";
        }

    }

    @GetMapping("/api/external/comment")
    public String saveExternalDataComment() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/comments";
        ResponseEntity<List<AddCommentRequest>> response =
                restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<AddCommentRequest>>() {
                });


        return "OK";
    }
}
