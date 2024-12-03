package app;

import app.controller.ArticleController;
import app.controller.ArticleFreemarkerController;
import app.controller.CommentController;
import app.repository.ArticleRepository;
import app.repository.CommentRepository;
import app.repository.memory.InMemoryArticleRepository;
import app.repository.memory.InMemoryCommentRepository;
import app.service.ArticleService;
import app.service.CommentService;
import app.template.TemplateFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Service;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Service service = Service.ignite();
        ObjectMapper objectMapper = new ObjectMapper();
        final ArticleRepository articleRepository = new InMemoryArticleRepository();
        final CommentRepository commentRepository = new InMemoryCommentRepository();
        final CommentService commentService = new CommentService(commentRepository, articleRepository);
        final ArticleService articleService = new ArticleService(articleRepository);

        Application application = new Application(
            List.of(
                new ArticleController(
                    service,
                    articleService,
                    objectMapper
                ),
                new CommentController(
                    service,
                    commentService,
                    objectMapper
                ),
                new ArticleFreemarkerController(
                    service,
                    articleService,
                    TemplateFactory.freeMarkerEngine()
                )
            )
        );
        application.start();
    }
}
