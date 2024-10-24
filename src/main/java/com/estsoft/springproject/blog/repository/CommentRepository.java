package com.estsoft.springproject.blog.repository;

import com.estsoft.springproject.blog.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticle_ArticleId(Long articleId);

}
