package org.dima.controller.response.article;

import java.util.List;
import org.dima.entity.Comment;
import org.dima.entity.id.ArticleId;

import java.util.Set;

public record ArticleUpdateResponse(
        ArticleId articleId,
        String name,
        Set<String> tags,
        List<Comment> comment
    ) {}
