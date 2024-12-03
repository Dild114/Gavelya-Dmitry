package app.entity;

import app.entity.id.ArticleId;
import app.entity.id.CommentId;

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
