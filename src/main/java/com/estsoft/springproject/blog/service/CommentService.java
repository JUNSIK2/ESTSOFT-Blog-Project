package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.dto.*;
import com.estsoft.springproject.blog.domain.entity.Article;
import com.estsoft.springproject.blog.domain.entity.Comment;
import com.estsoft.springproject.blog.repository.BlogRepository;
import com.estsoft.springproject.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public Comment saveComment(AddCommentRequest comment, Long articleId) {
        Article article = blogRepository.findById(articleId).orElseThrow();
        return commentRepository.save(new Comment(article, comment.getBody()));
    }

    public Comment findByComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        return optionalComment.orElse(new Comment());
    }

    @Transactional(readOnly = true)
    public Comment updateComment(Long commentId, UpdateCommentRequest request) {
        Comment comment = findByComment(commentId);
        comment.update(request.getBody());
        return comment;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public ArticleCommentResponse findByArticleComment(Long articleId) {
        Article article = blogRepository.findById(articleId).orElseThrow();
        List<Comment> commentList = commentRepository.findByArticle_ArticleId(articleId);
        List<CommentResponse> commentResponseList = commentList.stream().map(CommentResponse::new).toList();
        return new ArticleCommentResponse(article.toArticleResponse(), commentResponseList);
    }

    public List<Comment> saveCommentList(List<AddCommentRequest> requestList, Long ArticleId){
        blogRepository.findById(ArticleId);
        List<Comment> commentList = requestList.stream().map(addCommentRequest -> {
            addCommentRequest.setArticle(blogRepository.findById(ArticleId).orElseThrow());
            return addCommentRequest.toEntity();
        })
                .toList();
        return commentRepository.saveAll(commentList);

    }

}
