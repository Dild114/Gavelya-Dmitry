package app.service.exception.article;

public class ArticleCreateException extends Exception {
    public ArticleCreateException(String message) {
        super(message);
    }
    public ArticleCreateException(String message, Throwable cause) {
        super(message, cause);
    }

}