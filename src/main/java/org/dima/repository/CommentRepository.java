package org.dima.repository;

import org.dima.entity.Comment;
import org.dima.entity.id.CommentId;


public interface CommentRepository {

  CommentId generateId();

  void create(Comment comment);

  void delete(CommentId id);

}
