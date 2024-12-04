package org.dima.controller.request;

import org.dima.entity.id.ArticleId;

import java.util.List;
import java.util.Set;

public record ArticleUpdateRequest(ArticleId articleId, String name, Set<String> tags, List<String> comments) {}
