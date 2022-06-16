package com.robertowgsf.spaceflightnews.repository;

import com.robertowgsf.spaceflightnews.model.Article;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.repository.PagingAndSortingRepository;

@EnableScan
@EnableScanCount
public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> {
}
