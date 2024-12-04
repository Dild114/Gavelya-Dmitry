

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dima.Application;
import org.dima.controller.ArticleController;
import org.dima.controller.ArticleFreemarkerController;
import org.dima.controller.CommentController;
import org.dima.controller.response.article.ArticleCreateResponse;
import org.dima.repository.ArticleRepository;
import org.dima.repository.CommentRepository;
import org.dima.repository.memory.InMemoryArticleRepository;
import org.dima.repository.memory.InMemoryCommentRepository;
import org.dima.service.ArticleService;
import org.dima.service.CommentService;
import org.dima.template.TemplateFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import spark.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {

    private Service service;

    @BeforeEach
    void befofeEach() {
        service = Service.ignite();
    }

    @AfterEach
    void afterEach() {
        service.stop();
        service.awaitStop();
    }

    @Test
    void should201IfArticleIsSuccessfullyCreated() throws Exception {
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
        service.awaitInitialization();


        HttpResponse<String> response = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                { "name": "Test", "tags": ["first", "second", "third"] }"""
                        )
                    )
                    .uri(URI.create("http://localhost:%d/api/article".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );

        assertEquals(201, response.statusCode());
    }

    @Test
    void should201IfArticleIsSuccessfullyUpdated() throws Exception {
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
        service.awaitInitialization();


        HttpResponse<String> response = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                { "name": "Test", "tags": ["first", "second", "third"] }"""
                        )
                    )
                    .uri(URI.create("http://localhost:%d/api/article".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );
        assertEquals(201, response.statusCode());

        HttpResponse<String> nextResponse = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                { "name": "newTest", "tags": ["newFirst", "newSecond", "newThird"], "comments" : ["java", "python"] }  """
                        )
                    )
                    .uri(URI.create("http://localhost:%d/api/article/update/1".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );
        assertEquals(200, nextResponse.statusCode());
    }

    @Test
    void should404IfArticleIsNotFound() throws Exception {
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
        service.awaitInitialization();


        HttpResponse<String> response = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                { "name": "Test", "tags": ["first", "second", "third"] }"""
                        )
                    )
                    .uri(URI.create("http://localhost:%d/api/article".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );

        assertEquals(201, response.statusCode());

        HttpResponse<String> nextResponse = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:%d/api/article/999".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );
        assertEquals(404, nextResponse.statusCode());
    }

    @Test
    void should404IfArticleUpdateIdIsNotFound() throws Exception {
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
        service.awaitInitialization();


        HttpResponse<String> response = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                { "name": "Test", "tags": ["first", "second", "third"] }"""
                        )
                    )
                    .uri(URI.create("http://localhost:%d/api/article".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );

        assertEquals(201, response.statusCode());

        HttpResponse<String> nextResponse = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                { "name": "NewTest", "tags": ["first2", "second2", "third2"], "comments" : ["java", "python"] }  """
                        )
                    )
                    .uri(URI.create("http://localhost:%d/api/article/update/666".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );
        assertEquals(404, nextResponse.statusCode());
    }
}