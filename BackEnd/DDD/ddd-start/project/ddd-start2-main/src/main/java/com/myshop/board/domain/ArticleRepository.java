package com.myshop.board.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ArticleRepository extends Repository<Article, Long> {
  void save(Article article);

  Optional<Article> findById(Long id);
}
