package app.controller;

import app.controller.request.article.ArticleCreateRequest;
import app.controller.response.ErrorResponse;
import app.entity.Article;
import app.entity.id.ArticleId;
import app.service.ArticleService;
import app.service.exception.article.ArticleCreateException;
import app.service.exception.article.ArticleFindException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Response;
import spark.Request;


public class ArticleController implements Controller {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleController.class);
    private final Service service;
    private final ArticleService articleService;
    private final ObjectMapper objectMapper;

    public ArticleController(Service service, ArticleService articleService, ObjectMapper objectMapper) {
        this.service = service;
        this.articleService = articleService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void initializeEndpoints() {
        createArticle();
        deleteArticle();
        findAllArticle();
        findByIdArticle();
        updateArticle();
    }

    private void getArticle() {
        service.get("api/article/:articleId",
                (Request request, Response response) -> {
            response.type("application/json");
            ArticleId articleId = new ArticleId(Long.parseLong(request.queryParams("articleId")));
            try {
                 Article article = articleService.findById(articleId);
                 response.status(200);
                 return objectMapper.writeValueAsString(article);
            } catch (ArticleFindException e) {
                LOG.warn("Not found article Id: {}", articleId);
                response.status(404);
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
            }
                });
    }

    private void updateArticle() {
        service.post("api/article/:articleId",
                (Request request, Response response) -> {
            response.type("application/json");
            ArticleId articleId = new ArticleId(Long.parseLong(request.queryParams("articleId")));
//                    ArticleId articleId,
//                    String name,
//                    Set<String> tags,
//                    List<Comment> comment
            String name = request.queryParams("name");
            String content = request.queryParams("content");
                });
    }

    private void findByIdArticle() {
    }

    private void findAllArticle() {
    }

    private void deleteArticle() {
    }

    private void createArticle() {
        service.post("/api/article",
                (Request request, Response response) -> {
            response.type("application/json");
            String body = request.body();
            ArticleCreateRequest articleCreateRequest = objectMapper.readValue(body, ArticleCreateRequest.class);
            try {
                ArticleId articleId = articleService.create(articleCreateRequest.name(), articleCreateRequest.tags());
                LOG.debug("Article created successfully: {}", articleId);
                response.status(201);
                return objectMapper.writeValueAsString(articleId);
            } catch (ArticleCreateException e) {
                LOG.warn("ArticleCreateException on articleService.create()", e);
                response.status(400);
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
            }
        });
    }
}
