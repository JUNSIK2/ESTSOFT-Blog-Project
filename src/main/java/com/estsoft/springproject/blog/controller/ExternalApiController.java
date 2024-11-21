package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.dto.AddCommentRequest;
import com.estsoft.springproject.blog.domain.entity.Article;
import com.estsoft.springproject.blog.domain.entity.Comment;
import com.estsoft.springproject.blog.service.BlogService;
import com.estsoft.springproject.blog.service.CommentService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.core.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
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
            throw new NullPointerException("APi call failed");
        } else {
            List<Article> articleList = blogService.saveArticleList(response.getBody());
            return "OK";
        }

    }

    @GetMapping("/api/external/comment")
    public String saveExternalDataComment() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/comments";
        AddCommentRequest addCommentRequest = new AddCommentRequest();
        List<AddCommentRequest> addCommentRequestList = new ArrayList<>();
        ResponseEntity<List<String>> response = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<String>>() {}
        );

        response.getBody().forEach(s -> {
            addCommentRequest.setBody(extractValueUsingObjectMapper(s,"body"));
            addCommentRequestList.add(addCommentRequest);
            Long articleId = Long.valueOf(extractValueUsingObjectMapper(s,"postId"));
            commentService.saveCommentList(addCommentRequestList,articleId);
        });


        return "OK";
    }

    public String extractValueUsingObjectMapper(String jsonString, String key) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);
            JsonNode valueNode = rootNode.get(key);
            return valueNode != null ? valueNode.asText() : null;
        } catch (JsonProcessingException e) {
            log.error("JSON 파싱 에러", e);
            throw new RuntimeException("JSON 파싱 실패", e);
        }
    }
}
