package com.robertowgsf.spaceflightnews.controller;

import com.robertowgsf.spaceflightnews.model.Article;
import com.robertowgsf.spaceflightnews.repository.ArticleRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public List<Article> getArticles(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "20") int size, Pageable pageable) {
        return this.articleRepository.findAll(pageable).getContent();
    }
}
