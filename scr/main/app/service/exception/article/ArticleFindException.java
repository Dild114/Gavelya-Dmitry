package app.service.exception.article;

public class ArticleFindException extends Exception {
    public ArticleFindException(String message) {
        super(message, cause);
    }
}
