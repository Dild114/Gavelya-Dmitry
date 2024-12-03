package app.service.exception.article;

public class ArticleUpdateException extends Exception {
    public ArticleUpdateException(String message) {
        super(message, cause);
    }
}
