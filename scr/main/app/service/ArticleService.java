package app.service;

import app.entity.Article;
import app.entity.Comment;
import app.entity.id.ArticleId;
import app.repository.ArticleRepository;
import app.repository.exception.ArticleDuplicateException;
import app.repository.exception.ArticleNotFoundException;
import app.service.exception.article.ArticleCreateException;
import app.service.exception.article.ArticleDeleteByIdException;
import app.service.exception.article.ArticleFindException;
import app.service.exception.article.ArticleUpdateException;

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
            throw new ArticleUpdateException("Article with id " + articleId + " not found");
        }
         try {
             articleRepository.update(
                     article.withName(name)
                            .withComments(comments)
                            .withTags(tags)

             );
         } catch (ArticleNotFoundException e) {
             throw new ArticleUpdateException("Article with id " + articleId + " not found");
         }
    }
}
