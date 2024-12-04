package org.dima.repository;

import org.dima.entity.Article;
import java.util.List;
import org.dima.entity.id.ArticleId;

public interface ArticleRepository {

  ArticleId generateId();

  List<Article> findAll();

  Article findById(ArticleId id);

  void create(Article article);

  void update(Article article);

  void delete(ArticleId id);
}
