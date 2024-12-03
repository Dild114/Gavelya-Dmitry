package app.controller.request.article;

import app.entity.Article;
import app.entity.Comment;
import app.entity.id.ArticleId;

import java.util.List;
import java.util.Set;

public record ArticleUpdateRequest(ArticleId articleId, String name, Set<String> tags, List<Comment> comments) {}
