package app.controller.request.article;

import app.entity.Article;
import app.entity.Comment;

import java.util.List;
import java.util.Set;

public record ArticleUpdateRequest(Article articleId, String name, Set<String> tags, List<Comment> comments) {}
