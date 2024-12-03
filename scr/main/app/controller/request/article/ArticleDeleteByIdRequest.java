package app.controller.request.article;

import app.entity.Article;
import app.entity.id.ArticleId;

public record ArticleDeleteByIdRequest(ArticleId articleId) {}
