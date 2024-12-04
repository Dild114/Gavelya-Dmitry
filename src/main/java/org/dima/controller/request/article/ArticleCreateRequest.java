package org.dima.controller.request.article;

import java.util.Set;

public record ArticleCreateRequest(String name, Set<String> tags) {}
