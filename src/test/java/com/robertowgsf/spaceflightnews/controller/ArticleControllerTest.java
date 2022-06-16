package com.robertowgsf.spaceflightnews.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertowgsf.spaceflightnews.config.EasyRandomConfig;
import com.robertowgsf.spaceflightnews.model.Article;
import com.robertowgsf.spaceflightnews.repository.ArticleRepository;
import jdk.jshell.spi.ExecutionControl;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Import(EasyRandomConfig.class)
public class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private EasyRandom easyRandom;
    @Autowired
    private ArticleRepository articleRepository;

    @AfterEach
    void afterEach() {
        articleRepository.deleteAll();
    }

    @Test
    void shouldGetArticlesWithDefaultPagination() throws Exception {
        // TODO: Sort by id?
        List<Article> articles = easyRandom.objects(Article.class, 30).toList();
        articleRepository.saveAll(articles);

        // TODO: Return sorted list
        mockMvc.perform(get("/articles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(result -> {
                    List<Article> responseArticles = mapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });

                    // test if nested lists are equal as well. if not try to use usingRecursiveFieldByFieldElementComparator
                    assertEquals(20, responseArticles.size());
//                    assertThat(responseArticles).usingRecursiveComparison().isEqualTo(articles);
                });
    }

    @Test
    void shouldGetArticlesWithCustomPagination() throws Exception {
        List<Article> articles = easyRandom.objects(Article.class, 15).toList();
        articleRepository.saveAll(articles);

        mockMvc.perform(get("/articles?page=1&size=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    List<Article> responseArticles = mapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });

                    assertEquals(5, responseArticles.size());
//                    assertThat(responseArticles).usingRecursiveComparison().isEqualTo(articles);
                });
    }

    @Test
    void shouldGetArticleById() throws Exception {
        Article article = easyRandom.nextObject(Article.class);
        articleRepository.save(article);

        mockMvc.perform(get("/articles/" + article.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Article response = mapper.readValue(result.getResponse().getContentAsString(), Article.class);
                    assertEquals(article, response);
                });
    }

    @Test
    void shouldThrowNotFoundExceptionWhenArticleThatDoesNotExist() throws Exception {
        mockMvc.perform(get("/articles/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("Article not found", result.getResolvedException().getMessage()));
    }

    @Test
    void shouldAddArticle() throws Exception {
        Article article = easyRandom.nextObject(Article.class);

        mockMvc.perform(post("/articles")
//                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(article)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    Article response = mapper.readValue(result.getResponse().getContentAsString(), Article.class);
                    assertEquals(article, response);
                });
    }

    // shouldThrowArticleAlreadyExistsWhenAddingExistingArticle?

    @Test
    void shouldUpdateArticle() throws Exception {
        throw new UnsupportedOperationException();
    }

    // shouldThrowArticleNotFoundWhenUpdatingAnNonExistingArticle

    @Test
    void shouldDeleteArticle() throws Exception {
        throw new UnsupportedOperationException();
    }
}
