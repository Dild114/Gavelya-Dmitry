package org.dima.repository.exception;

public class ArticleDuplicateException extends RuntimeException {
    public ArticleDuplicateException(String message) {
        super(message);
    }
    public ArticleDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
