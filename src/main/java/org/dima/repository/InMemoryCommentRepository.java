package org.dima.repository;

import org.dima.entity.Comment;
import org.dima.entity.id.CommentId;
import org.dima.repository.exception.CommentDuplicateException;
import org.dima.repository.exception.CommentNotFoundException;

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
    public void create(Comment comment) {
        if (commentMap.get(comment.getId()) != null) {
            throw new CommentDuplicateException("Comment with id " + comment.getId() + " already exists");
        }
        commentMap.put(comment.getId(), comment);
    }

    @Override
    public void delete(CommentId id) {
        if (commentMap.remove(id) == null) {
            throw new CommentNotFoundException("Comment with id " + id + " not found");
        }
        commentMap.remove(id);
    }
}
