package app.controller;

import app.controller.request.comment.CommentCreateByIdArticleRequest;
import app.controller.request.comment.CommentDeleteByIdArticleRequest;
import app.controller.response.ErrorResponse;
import app.entity.id.CommentId;
import app.repository.exception.CommentDuplicateException;
import app.repository.exception.CommentNotFoundException;
import app.service.CommentService;
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

    public CommentController(Service service ,CommentService commentService, ObjectMapper objectMapper) {
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
            String body = request.body();
            CommentCreateByIdArticleRequest commentCreate = objectMapper.
                    readValue(body, CommentCreateByIdArticleRequest.class);

            try {
                CommentId commentId = commentService.create(commentCreate.articleId(), commentCreate.comment());
                response.status(201);
                LOG.warn("New comment created: {}", commentId);
                return objectMapper.writeValueAsString(commentId);
            } catch (CommentDuplicateException e) {
                response.status(400);
                LOG.warn("Duplicate comment: {} with id: {}", e.getMessage(), commentCreate.articleId());
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
            }
                });
    }

    private void deleteComment() {
        service.delete("api/article/comment/:articleId",
                (Request request, Response response) -> {
            response.type("application/json");
            CommentDeleteByIdArticleRequest commentDelete = objectMapper.
                    readValue(request.body(), CommentDeleteByIdArticleRequest.class);
            try {
                commentService.delete(commentDelete.commentId());
                response.status(204);
                LOG.warn("Comment deleted: {}", commentDelete.commentId());
                return objectMapper.writeValueAsString(commentDelete.commentId());
            } catch (CommentNotFoundException e) {
                response.status(404);
                LOG.warn("Comment not found: {}", commentDelete.commentId(), e);
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
            }
                });
    }
}