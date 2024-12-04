package org.dima.repository;

import org.dima.entity.Article;
import org.dima.entity.id.ArticleId;
import org.dima.repository.exception.ArticleDuplicateException;
import org.dima.repository.exception.ArticleNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryArticleRepository implements ArticleRepository {
    private final AtomicLong nextId = new AtomicLong(0);
    private final Map<ArticleId, Article> articleMap = new ConcurrentHashMap<>();

    @Override
    public ArticleId generateId() {
        return new ArticleId(nextId.incrementAndGet());
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(articleMap.values());
    }

    @Override
    public Article findById(ArticleId id) {
        Article article = articleMap.get(id);
        if (article == null) {
            throw new ArticleNotFoundException("Article with id " + id + " not found");
        }
        return article;
    }

    @Override
    public void create(Article article) {
        if (articleMap.get(article.getId()) != null) {
            throw new ArticleDuplicateException("Article with id " + article.getId() + " already exists");
        }
        articleMap.put(article.getId(), article);
    }

    @Override
    public void update(Article article) {
        if (articleMap.get(article.getId()) == null) {
            throw new ArticleNotFoundException("Article with id " + article.getId() + " not found");
        }
        articleMap.put(article.getId(), article);
    }

    @Override
    public void delete(ArticleId id) {
        if (articleMap.remove(id) == null) {
            throw new ArticleNotFoundException("Article with id " + id + " not found");
        }
        articleMap.remove(id);
    }
}
