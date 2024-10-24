package com.estsoft.springproject.blog.service;

import com.estsoft.springproject.blog.domain.dto.AddArticleRequest;
import com.estsoft.springproject.blog.domain.dto.UpdateArticleRequest;
import com.estsoft.springproject.blog.domain.entity.Article;
import com.estsoft.springproject.blog.repository.BlogRepository;
import jakarta.persistence.AttributeConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article saveArticle(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findBy(Long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found id : " + id));
    }

    public void deleteBy(Long id) {
        blogRepository.deleteById(id);
    }

    public List<Article> saveArticleList(List<AddArticleRequest> requests){
        List<Article> articleList = requests.stream().map(AddArticleRequest::toEntity).toList();
        return blogRepository.saveAll(articleList);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = findBy(id);
        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
