package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.domain.entity.Article;
import com.estsoft.springproject.blog.repository.BlogRepository;

import com.estsoft.springproject.blog.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc
@SpringBootTest
class BlogControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BlogRepository repository;

    @Autowired
    private BlogService service;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        repository.deleteAll();
    }

    @Test
    public void addArticle() throws Exception {
        // given
        AddArticleRequest request = new AddArticleRequest("제목", "내용");

        // 직렬화
        String json = objectMapper.writeValueAsString(request);

        // When : API 요청
        ResultActions resultActions = mockMvc.perform(post("/articles")
                .contentType(MediaType.APPLICATION_JSON).content(json));

        // Then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("title").value("제목"))
                .andExpect(jsonPath("content").value(request.getContent()));

        List<Article> articleList = repository.findAll();
        Assertions.assertThat(articleList.size()).isEqualTo(1);
    }

    @Test
    public void getAll() throws Exception {
        Article article = repository.save(new Article("제목", "내용"));

        ResultActions actions = mockMvc.perform(get("/articles").accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(article.getTitle()))
                .andExpect(jsonPath("$[0].content").value(article.getContent()));
    }

    @Test
    public void getOne() throws Exception {
        Article article = repository.save(new Article("제목", "내용"));

        ResultActions actions = mockMvc.perform(get("/articles/{id}", article.getArticleId()).accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("title").value(article.getTitle()))
                .andExpect(jsonPath("content").value(article.getContent()));
    }

    @Test
    public void getOneException() throws Exception {
        ResultActions actions = mockMvc.perform(get("/articles/{id}", 1L).accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isBadRequest());

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> service.findBy(1L));
    }

    @Test
    public void deleteOne() throws Exception {
        Article article = repository.save(new Article("제목", "내용"));

        ResultActions actions = mockMvc.perform(delete("/articles/{id}", article.getArticleId()));

        actions.andExpect(status().isOk());
        List<Article> articleList = repository.findAll();
        Assertions.assertThat(articleList).isEmpty();
    }

    @Test
    public void updateArticle() throws Exception {
        Article article = repository.save(new Article("제목", "내용"));

        UpdateArticleRequest request = new UpdateArticleRequest("변경 제목", "변경 내용");
        String updateJsonContent = objectMapper.writeValueAsString(request);

        ResultActions actions = mockMvc.perform(put("/articles/{id}", article.getArticleId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJsonContent));

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("title").value(request.getTitle()));
    }

    @Test
    public void updateArticleException() throws Exception {
        UpdateArticleRequest request = new UpdateArticleRequest("변경 제목", "변경 내용");
        String updateJsonContent = objectMapper.writeValueAsString(request);

        ResultActions actions = mockMvc.perform(put("/articles/{id}", 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJsonContent));

        actions.andExpect(status().isBadRequest());

        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class,() -> service.update(2L,request));
    }

}