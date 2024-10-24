package com.estsoft.springproject.blog.controller;

import com.estsoft.springproject.blog.domain.dto.ArticleViewResponse;
import com.estsoft.springproject.blog.domain.entity.Article;
import com.estsoft.springproject.blog.service.BlogService;
import com.estsoft.springproject.user.domain.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {
    private final BlogService service;

    @Autowired
    public BlogPageController(BlogService service) {
        this.service = service;
    }

    @GetMapping("/articles")
    public String showArticle(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<Article> articles = service.findAll();
        List<ArticleViewResponse> articleViewResponses = articles.stream()
                .map(ArticleViewResponse::new)
                .toList();
        model.addAttribute("articles", articleViewResponses);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users users2 = (Users) authentication.getPrincipal(); // @AuthenticationPrincipal 과 같은 객체
//
//        System.out.println();
//        System.out.println(users1.toString());
//        System.out.println(users2.toString());
//
//        System.out.println(authentication.getDetails());
//        System.out.println(authentication.getCredentials());
//        System.out.println(authentication.getAuthorities());
//        System.out.println();

        Article article = service.findBy(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }

    @GetMapping("/new-article")
    public String addArticle(@RequestParam(required = false) Long id, Model model){
        if (id == null){
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            model.addAttribute("article", new ArticleViewResponse(service.findBy(id)));
        }
        return "newArticle";
    }


}
