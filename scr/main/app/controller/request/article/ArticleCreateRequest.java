package app.controller.request.article;

import app.entity.Article;

import java.util.Set;

public record ArticleCreateRequest(String name, Set<String> tags) {}
