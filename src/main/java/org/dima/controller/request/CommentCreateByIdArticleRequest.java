package org.dima.controller.request;

import org.dima.entity.id.ArticleId;

public record CommentCreateByIdArticleRequest(ArticleId articleId, String comment) {}
