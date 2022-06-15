package com.robertowgsf.spaceflightnews.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robertowgsf.spaceflightnews.model.Article;
import com.robertowgsf.spaceflightnews.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ArticleControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper mapper;
    @Autowired private ArticleRepository articleRepository;

    @Test
    void shouldGetPaginatedArticles() throws Exception {
        List<Article> articles = mockArticles();
        articleRepository.saveAll(articles);

        mockMvc.perform(get("/articles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(result -> {
                    List<Article> responseArticles = mapper.readValue(result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });

                    // test if nested lists are equal as well. if not try to use usingRecursiveFieldByFieldElementComparator
                    assertThat(responseArticles).usingRecursiveComparison().isEqualTo(articles);
                });

        articleRepository.deleteAll(articles);
    }

    private List<Article> mockArticles() {
        return List.of(new Article(), new Article(), new Article(), new Article(), new Article(), new Article(),
                new Article(), new Article(), new Article(), new Article());
    }
}
