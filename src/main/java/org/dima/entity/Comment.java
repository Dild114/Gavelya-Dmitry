package org.dima.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.dima.entity.id.ArticleId;
import org.dima.entity.id.CommentId;

public class Comment {
  CommentId id;
  ArticleId articleId;
  String comment;

  public Comment(String comment, ArticleId articleId, CommentId id) {
    this.comment = comment;
    this.articleId = articleId;
    this.id = id;
  }


  public CommentId getId() {
    return id;
  }

  public String getComment() {
    return comment;
  }

  public ArticleId getArticleId() {
    return articleId;
  }
}
