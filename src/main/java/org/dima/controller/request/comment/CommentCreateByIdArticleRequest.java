package org.dima.controller.request.comment;

import org.dima.entity.id.ArticleId;

public record CommentCreateByIdArticleRequest(ArticleId articleId, String comment) {}
