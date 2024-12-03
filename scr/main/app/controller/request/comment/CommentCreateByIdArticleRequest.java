package app.controller.request.comment;

import app.entity.id.ArticleId;

public record CommentCreateByIdArticleRequest(ArticleId articleId, String comment) {}
