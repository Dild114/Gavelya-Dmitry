package app.service.exception.comment;

public class CommentCreateByIdArticleException extends Exception {
    public CommentCreateByIdArticleException(String message) {
        super(message);
    }
    public CommentCreateByIdArticleException(String message, Throwable cause) {
        super(message, cause);
    }
}
