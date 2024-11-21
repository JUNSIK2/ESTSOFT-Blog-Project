package com.estsoft.springproject.book.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.estsoft.springproject.book.domain.entity.Book;
import com.estsoft.springproject.book.repository.BookRepository;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	private BookRepository bookRepository;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		bookRepository.deleteAll();
	}

	@Test
	void allBooks() throws Exception {
		bookRepository.save(new Book("1234","junsik","gulsunri"));

		String url = "/books";
		ResultActions resultActions = mockMvc.perform(get(url));

		resultActions.andExpect(status().isOk())
			.andExpect(view().name("bookManagement"))
			.andExpect(model().attribute("bookList", hasSize(1)));
	}
}