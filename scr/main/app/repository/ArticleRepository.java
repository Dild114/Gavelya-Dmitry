package app.repository;

import app.entity.Article;
import java.util.List;
import app.entity.id.ArticleId;

public interface ArticleRepository {

  ArticleId generateId();

  List<Article> findAll();

  Article findById(ArticleId id);

  void create(Article article);

  void update(Article article);

  void delete(ArticleId id);
}
