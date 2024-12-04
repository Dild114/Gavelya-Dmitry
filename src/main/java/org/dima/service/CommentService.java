package org.dima.service;

import org.dima.entity.Article;
import org.dima.entity.Comment;
import org.dima.entity.id.ArticleId;
import org.dima.entity.id.CommentId;
import org.dima.repository.ArticleRepository;
import org.dima.repository.CommentRepository;
import org.dima.repository.exception.ArticleNotFoundException;
import org.dima.repository.exception.CommentDuplicateException;
import org.dima.repository.exception.CommentNotFoundException;
import org.dima.service.exception.comment.CommentCreateByIdArticleException;

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

    public void delete(CommentId commentId, ArticleId articleId) {
        try {
            commentRepository.delete(commentId);
            List<Comment> commentList = null;
            for (Comment comment : articleRepository.findById(articleId).getComments()) {
                commentList = new ArrayList<>();
                if (!comment.getId().equals(commentId)) {
                    commentList.add(comment);
                }
            }
            Article newArticle = articleRepository.findById(articleId).withComments(commentList);
            articleRepository.update(newArticle);
        } catch (CommentNotFoundException e) {
            throw new CommentNotFoundException("Comment does not exist", e);
        }
    }
}
