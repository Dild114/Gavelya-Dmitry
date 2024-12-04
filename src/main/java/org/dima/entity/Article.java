package org.dima.entity;

import org.dima.entity.id.ArticleId;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Article {
  String name;
  ArticleId id;
  Set<String> tags;
  List<Comment> comments;

  public Article(String name, ArticleId id, Set<String> tags, List<Comment> comments) {
    this.name = name;
    this.id = id;
    this.tags = tags;
    this.comments = comments;
  }

  public ArticleId getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getTags() {
    return tags.toString();
  }

  public List<Comment> getComments() {
    if (comments != null) {
      return new ArrayList<>(comments);
    }
    return null;
  }

  public Article withName(String newName) {
    return new Article(newName, this.id, this.tags, this.comments);
  }

  public Article withTags(Set<String> newTags) {
    return new Article(this.name, this.id, newTags, this.comments);
  }

  public Article withComments(List<Comment> newComments) {
    return new Article(this.name, this.id, this.tags, newComments);
  }


}
