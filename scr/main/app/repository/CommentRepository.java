package app.repository;

import app.entity.Comment;
import app.entity.id.CommentId;
import app.repository.exception.CommentNotFoundException;

import java.util.List;


public interface CommentRepository {

  CommentId generateId();

  void create(Comment comment);

  void delete(CommentId id);

}
