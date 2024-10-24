package com.estsoft.springproject.controller;

import com.estsoft.springproject.domain.entity.Member;
import com.estsoft.springproject.repository.MemberRepository;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest            // 테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc    // MockMvc 생성 및 자동 구성
public class MemberControllerTest {
    @Autowired
    WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetAllMember() throws Exception {
        // Given
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // When
        ResultActions resultActions = mockMvc.perform(get("/members") // 1
                .accept(MediaType.APPLICATION_JSON));  // 2
        // Then
        resultActions.andExpect(status().isOk()) // 3
                // 4. 응답의 0번째 값이 DB에 저장한 값과 같은지 검증
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}
