package org.dima.controller.request.comment;


import com.fasterxml.jackson.annotation.JsonProperty;

public record CommentDeleteByIdArticleRequest(
    @JsonProperty("commentId") long commentId,
    @JsonProperty("articleId") long articleId
) {}
