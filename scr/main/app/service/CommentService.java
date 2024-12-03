package app.service;

import app.entity.Article;
import app.entity.Comment;
import app.entity.id.ArticleId;
import app.entity.id.CommentId;
import app.repository.ArticleRepository;
import app.repository.CommentRepository;
import app.repository.exception.ArticleNotFoundException;
import app.repository.exception.CommentDuplicateException;
import app.repository.exception.CommentNotFoundException;
import app.service.exception.comment.CommentCreateByIdArticleException;

import java.util.ArrayList;
import java.util.List;

public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    public CommentId create(ArticleId articleId, String text) throws CommentCreateByIdArticleException {
        CommentId commentId = commentRepository.generateId();
        Comment comment = new Comment(text, articleId, commentId);
        try {
            commentRepository.create(comment);
        } catch (CommentDuplicateException e) {
            throw new CommentDuplicateException("Comment already exists", e);
        }
        try {
            Article article = articleRepository.findById(articleId);
            List<Comment> commentList = new ArrayList<>();
            if (article.getComments() != null) {
                commentList = article.getComments();
                commentList.add(comment);
            } else {
                commentList.add(comment);
            }
            articleRepository.update(article.withComments(commentList));
            return commentId;
        } catch (ArticleNotFoundException e) {
            // article не понимает, что ошибка связанна c комментарием, поэтому оно обрабатывается тут
            throw new CommentCreateByIdArticleException("Article id does not exist", e);
        }
    }

    public void delete(CommentId commentId) {
        try {
            commentRepository.delete(commentId);
        } catch (CommentNotFoundException e) {
            throw new CommentNotFoundException("Comment does not exist", e);
        }
    }
}
