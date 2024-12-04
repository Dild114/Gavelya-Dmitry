package org.dima.service.exception;

public class ArticleDeleteByIdException extends Exception {
    public ArticleDeleteByIdException(String message) {
        super(message);
    }
    public ArticleDeleteByIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
