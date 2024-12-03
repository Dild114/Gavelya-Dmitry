package app.repository.memory;

import app.entity.Comment;
import app.entity.id.CommentId;
import app.repository.CommentRepository;
import app.repository.exception.CommentDuplicateException;
import app.repository.exception.CommentNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryCommentRepository implements CommentRepository {
    private final AtomicLong nextId = new AtomicLong(0);
    private final Map<CommentId, Comment> commentMap = new ConcurrentHashMap<>();

    @Override
    public CommentId generateId() {
        return new CommentId(nextId.incrementAndGet());
    }

    @Override
    public List<Comment> findAll() {
        return new ArrayList<>(commentMap.values());
    }

    @Override
    public Comment findById(CommentId id) {
        Comment comment = commentMap.get(id);
        if (comment == null) {
            throw new CommentNotFoundException("Comment with id " + id + " not found");
        }
        return comment;
    }

    @Override
    public void create(Comment comment) {
        if (commentMap.get(comment.getId()) != null) {
            throw new CommentDuplicateException("Comment with id " + comment.getId() + " already exists");
        }
        commentMap.put(comment.getId(), comment);
    }

    @Override
    public void update(Comment comment) {
        if (commentMap.get(comment.getId()) == null) {
            throw new CommentNotFoundException("Comment with id " + comment.getId() + " not found");
        }
        commentMap.put(comment.getId(), comment);
    }

    @Override
    public void delete(CommentId id) {
        if (commentMap.remove(id) == null) {
            throw new CommentNotFoundException("Comment with id " + id + " not found");
        }
    }
}
