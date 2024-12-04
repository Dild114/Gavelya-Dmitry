package org.dima.controller;

import org.dima.controller.request.CommentCreateByIdArticleRequest;
import org.dima.controller.request.CommentDeleteByIdArticleRequest;
import org.dima.controller.response.ErrorResponse;
import org.dima.entity.Article;
import org.dima.entity.id.ArticleId;
import org.dima.entity.id.CommentId;
import org.dima.repository.exception.CommentDuplicateException;
import org.dima.repository.exception.CommentNotFoundException;
import org.dima.service.CommentService;
import org.dima.service.exception.CommentCreateByIdArticleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Response;
import spark.Request;

public class CommentController implements Controller {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);
    Service service;
    CommentService commentService;
    ObjectMapper objectMapper;

    public CommentController(Service service, CommentService commentService, ObjectMapper objectMapper) {
        this.service = service;
        this.commentService = commentService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void initializeEndpoints() {
        createComment();
        deleteComment();
    }

    private void createComment() {
        service.post("api/article/comment/:articleId",
            (Request request, Response response) -> {
                response.type("application/json");
                ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
                CommentCreateByIdArticleRequest commentCreate =
                    objectMapper.readValue(request.body(), CommentCreateByIdArticleRequest.class);

                try {
                    CommentId commentId = commentService.create(articleId, commentCreate.comment());
                    response.status(201);
                    LOG.debug("New comment created: {}", commentId);
                    return objectMapper.writeValueAsString(commentId);
                } catch (CommentCreateByIdArticleException e) {
                    response.status(400);
                    LOG.warn("Exception comment: {} with id: {}", e.getMessage(), articleId);
                    return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
                }
            });
    }

    private void deleteComment() {
        // когда url api/article/comment то запрос уходит на deleteArticle, deleteArticle воспринимаем comment, как :articleId
        service.delete("api/article/delete/comment/:articleId/:commentId",
            (Request request, Response response) -> {
                response.type("application/json");
                ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
                CommentId commentId = new CommentId(Long.parseLong(request.params("commentId")));

                try {
                    commentService.delete(commentId, articleId);
                    response.status(204);
                    LOG.debug("Comment deleted: {}", commentId);
                    return objectMapper.writeValueAsString(commentId);
                } catch (CommentNotFoundException e) {
                    response.status(404);
                    LOG.warn("Comment not found: {}", commentId, e);
                    return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
                }
            });
    }
}