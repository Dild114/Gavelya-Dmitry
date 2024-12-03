package app.controller.response.article;

import java.util.List;
import app.entity.Comment;
import app.entity.id.ArticleId;

import java.util.Set;

public record ArticleUpdateResponse(
        ArticleId articleId,
        String name,
        Set<String> tags,
        List<Comment> comment
    ) {}
