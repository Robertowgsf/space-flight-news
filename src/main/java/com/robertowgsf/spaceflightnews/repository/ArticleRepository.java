package com.robertowgsf.spaceflightnews.repository;

import com.robertowgsf.spaceflightnews.model.Article;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {
}
