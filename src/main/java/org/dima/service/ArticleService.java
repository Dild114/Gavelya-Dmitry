package org.dima.service;

import org.dima.entity.Article;
import org.dima.entity.Comment;
import org.dima.entity.id.ArticleId;
import org.dima.repository.ArticleRepository;
import org.dima.repository.exception.ArticleDuplicateException;
import org.dima.repository.exception.ArticleNotFoundException;
import org.dima.service.exception.article.ArticleCreateException;
import org.dima.service.exception.article.ArticleDeleteByIdException;
import org.dima.service.exception.article.ArticleFindException;
import org.dima.service.exception.article.ArticleUpdateException;
import java.util.List;
import java.util.Set;

public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(ArticleId id) throws ArticleFindException {
        try {
            return articleRepository.findById(id);
        } catch (ArticleNotFoundException e) {
            throw new ArticleFindException("Article with id " + id + " not found", e);
        }
    }
    public void delete(ArticleId id) throws ArticleDeleteByIdException {
        try {
            articleRepository.delete(id);
        } catch (ArticleNotFoundException e) {
            throw new ArticleDeleteByIdException("Article with id " + id + " not found", e);
        }
    }
    public ArticleId create(String name, Set<String> tags) throws ArticleCreateException {
        ArticleId articleId = articleRepository.generateId();
        Article article = new Article(name, articleId, tags, null);
        try {
            articleRepository.create(article);
        } catch (ArticleDuplicateException e) {
            throw new ArticleCreateException("Article with id " + article.getId() + " already exists", e);
        }
        return articleId;
    }

    public void update(ArticleId articleId, String name, Set<String> tags, List<Comment> comments) throws ArticleUpdateException {
        Article article;
        try {
            article = articleRepository.findById(articleId);
        } catch (ArticleNotFoundException e) {
            throw new ArticleUpdateException("Article with id " + articleId + " not found", e);
        }
         try {
             articleRepository.update(
                     article.withName(name)
                            .withComments(comments)
                            .withTags(tags)

             );
         } catch (ArticleNotFoundException e) {
             throw new ArticleUpdateException("Article with id " + articleId + " not found", e);
         }
    }
}
