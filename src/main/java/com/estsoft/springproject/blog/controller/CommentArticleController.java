package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.AddCommentRequest;
import com.estsoft.springproject.blog.domain.dto.ArticleCommentResponse;
import com.estsoft.springproject.blog.domain.dto.CommentResponse;
import com.estsoft.springproject.blog.domain.dto.UpdateCommentRequest;
import com.estsoft.springproject.blog.domain.entity.Comment;
import com.estsoft.springproject.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentArticleController {
    private final CommentService service;

    @Autowired
    public CommentArticleController(CommentService service) {
        this.service = service;
    }

    //  댓글 정보 생성
    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentResponse> addComment(@RequestBody AddCommentRequest request, @PathVariable Long articleId) {
        Comment comment = service.saveComment(request, articleId);
        return ResponseEntity.ok().body(new CommentResponse(comment));
    }
    //  댓글 정보 조회
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> findComment(@PathVariable Long commentId) {
        Comment comment = service.findByComment(commentId);
        return ResponseEntity.ok().body(new CommentResponse(comment));
    }
    //  댓글 정보 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> modifyComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequest request) {
        Comment comment = service.updateComment(commentId, request);
        return ResponseEntity.ok().body(new CommentResponse(comment));
    }
    //  댓글 정보 삭제
    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId) {
        service.deleteComment(commentId);
    }

    //  게시글과 댓글 정보를 한번에 조회
    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<ArticleCommentResponse> findArticleComment(@PathVariable Long articleId) {
        return ResponseEntity.ok().body(service.findByArticleComment(articleId));
    }

}
