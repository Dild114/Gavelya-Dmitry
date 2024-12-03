package app.controller.request.comment;

import app.entity.Article;

public record CommentCreateByIdArticleRequest(Article articleId, String comment) {}
