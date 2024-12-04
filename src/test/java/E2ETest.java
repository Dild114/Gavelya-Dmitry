import com.fasterxml.jackson.databind.ObjectMapper;
import org.dima.Application;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

class E2ETest {

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
    void FullTest() throws Exception {
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

        HttpResponse<String> responseCommentCreate = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                { "comment": "Test" }"""
                        )
                    )
                    .uri(URI.create("http://localhost:%d/api/article/comment/1".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );

        assertEquals(201, responseCommentCreate.statusCode());

        HttpResponse<String> responseForGetAllArticle = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:%d/api/article/all".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );
        assertEquals(201, response.statusCode());

        HttpResponse<String> responseGetArticleById = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("http://localhost:%d/api/article/1".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );
        assertEquals(200, responseGetArticleById.statusCode());

        HttpResponse<String> responseForUpdateArticle = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            """
                                { "name": "Test", "tags": ["first", "second", "third"], "comments" : ["commentFirst", "commentSecond"] }"""
                        )
                    )
                    .uri(URI.create("http://localhost:%d/api/article/update/1".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );
        assertEquals(200, responseForUpdateArticle.statusCode());

        HttpResponse<String> responseForDeleteComment = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create("http://localhost:%d/api/article/delete/comment/1/1".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );
        assertEquals(204, responseForDeleteComment.statusCode());

        HttpResponse<String> responseForDeleteArticle = HttpClient.newHttpClient()
            .send(
                HttpRequest.newBuilder()
                    .DELETE()
                    .uri(URI.create("http://localhost:%d/api/article/1".formatted(service.port())))
                    .build(),
                HttpResponse.BodyHandlers.ofString(UTF_8)
            );
        assertEquals(204, responseForDeleteComment.statusCode());

    }
}