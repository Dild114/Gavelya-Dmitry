package app.repository;

import app.entity.Comment;
import app.entity.id.CommentId;
import app.repository.exception.CommentNotFoundException;

import java.util.List;


public interface CommentRepository {

  CommentId generateId();

  List<Comment> findAll();

  Comment findById(CommentId id) throws CommentNotFoundException;

  void create(Comment comment);

  void update(Comment comment);

  void delete(CommentId id);

}
