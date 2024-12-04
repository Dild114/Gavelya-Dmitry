package org.dima;

import org.dima.controller.ArticleController;
import org.dima.controller.ArticleFreemarkerController;
import org.dima.controller.CommentController;
import org.dima.repository.ArticleRepository;
import org.dima.repository.CommentRepository;
import org.dima.repository.InMemoryArticleRepository;
import org.dima.repository.InMemoryCommentRepository;
import org.dima.service.ArticleService;
import org.dima.service.CommentService;
import org.dima.template.TemplateFactory;
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
