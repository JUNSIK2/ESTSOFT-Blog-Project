package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.dto.ArticleResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.domain.entity.Article;
import com.estsoft.springproject.blog.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController        // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
@RequestMapping("/api")
@Tag(name = "블로그 저장/수정/삭제/조회 API")

public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request) { // RequestBody로 요청 본문 값 매핑
        Article article = blogService.saveArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article.toArticleResponse());
    }

    @Operation(summary = "블로그 전체 목록 보기", description = "블로그 메인 화면에서 보여주는 전체 목록")
    @ApiResponse(responseCode = "100", description = "요청 성공", content = @Content(mediaType = "application/json"))
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> getAll() {
        List<ArticleResponse> articleResponseList = blogService.findAll().stream()
                .map(Article::toArticleResponse)
                .toList();

        return ResponseEntity.ok(articleResponseList);
    }

    @Parameter(name = "id", description = "블로그 글 ID", example = "45")
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> getOne(@PathVariable Long id) {
        Article article = blogService.findBy(id);
        return ResponseEntity.ok(article.toArticleResponse());
    }

    //    @RequestMapping(method = RequestMethod.DELETE, value = "/articles/{id}")
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        blogService.deleteBy(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> updateById(@RequestBody UpdateArticleRequest request, @PathVariable Long id) {
        ArticleResponse response = blogService.update(id, request).toArticleResponse();
        return ResponseEntity.ok().body(response);
    }

//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
}
