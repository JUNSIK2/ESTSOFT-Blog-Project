package com.estsoft.springproject.blog.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.entity.Article;
import com.estsoft.springproject.blog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class BlogControllerTest2 {
	@InjectMocks
	BlogController blogController;

	@Mock
	BlogService blogService;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
	}

	@Test
	public void testSave() throws Exception {
		// given (직렬화)
		String title = "test_title";
		String content = "test_content";
		AddArticleRequest request = new AddArticleRequest(title, content);
		ObjectMapper mapper = new ObjectMapper();
		String articleJson = mapper.writeValueAsString(request);

		// stub (service.saveArticle 호출시 위에서 만든 article 을 리턴 하도록 stub 처리)
		Mockito.when(blogService.saveArticle(any()))
			.thenReturn(new Article(title, content));

		// when
		ResultActions resultActions = mockMvc.perform(post("/api/articles")
			.content(articleJson)
			.contentType(MediaType.APPLICATION_JSON));

		// then
		resultActions.andExpect(status().isCreated())
			.andExpect(jsonPath("title").value(title))
			.andExpect(jsonPath("content").value(content));
	}

	@Test
	public void testDelete() throws Exception {
	}

	@Test
	public void testFindById() throws Exception {
	}

	@Test
	public void testFindAll() throws Exception {
	}
}
