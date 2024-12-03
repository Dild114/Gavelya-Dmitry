package app.controller;

import app.controller.request.article.ArticleCreateRequest;
import app.controller.request.article.ArticleUpdateRequest;
import app.controller.response.ErrorResponse;
import app.entity.Article;
import app.entity.id.ArticleId;
import app.repository.exception.ArticleNotFoundException;
import app.service.ArticleService;
import app.service.exception.article.ArticleCreateException;
import app.service.exception.article.ArticleFindException;
import app.service.exception.article.ArticleUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Response;
import spark.Request;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
        service.post("api/article/update/:articleId",
                (Request request, Response response) -> {
            response.type("application/json");
            ArticleId articleId = new ArticleId(Long.parseLong(request.queryParams("articleId")));
            ArticleUpdateRequest articleUpdateRequest = objectMapper.readValue(request.body(), ArticleUpdateRequest.class);
            try {
                articleService.update(
                        articleUpdateRequest.articleId(),
                        articleUpdateRequest.name(),
                        articleUpdateRequest.tags(),
                        articleUpdateRequest.comments()
                        );
                response.status(200);
                return objectMapper.writeValueAsString(articleId);
            } catch (ArticleUpdateException e) {
                LOG.warn("Not found article Id: {} and exception", articleId, e);
                response.status(404);
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
            }
                });
    }

    private void findByIdArticle() {
        service.post("api/article/id/:articleId",
            (Request request, Response response) -> {
            response.type("application/json");
            ArticleId articleId = new ArticleId(Long.parseLong(request.queryParams("articleId")));
            try {
                Article article = articleService.findById(articleId);
                response.status(200);
                return objectMapper.writeValueAsString(article);
            } catch (ArticleFindException e) {
                LOG.warn("Not found article Id: {} and exception {}", articleId, e.getMessage());
                response.status(404);
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
            }
            });
    }

    private void findAllArticle() {
        service.get("api/article",
                (Request request, Response response) -> {
            response.type("application/json");

            try {
                List<Article> articles = articleService.findAll();
                List<Map<String, String>> articlesList = new ArrayList<>();
                for (int i = 0; i < articles.size(); i++) {
                    articlesList.get(i).put("articleId", String.valueOf(articles.get(i).getId().id()));
                    articlesList.get(i).put("name", articles.get(i).getName());
                    articlesList.get(i).put("tags", articles.get(i).getTags());
                    articlesList.get(i).put("comments", articles.get(i).
                            getComments().
                            stream().
                            map(Object::toString).
                            collect(Collectors.joining(", ")));
                }
                LOG.info("Found {} articles", articlesList.size());
                response.status(200);
                return objectMapper.writeValueAsString(articlesList);
            } catch (ArticleNotFoundException e) {
                response.status(404);
                LOG.warn("Not found article", e);
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
            }
                });
    }

    private void deleteArticle() {
        service.delete("api/article/:articleId",
                (Request request, Response response) -> {
            response.type("application/json");
            ArticleId articleId = new ArticleId(Long.parseLong(request.queryParams("articleId")));
            try {
                articleService.delete(articleId);
                LOG.info("Deleted article Id: {}", articleId);
                response.status(200);
                return objectMapper.writeValueAsString("Article deleted");
            } catch (ArticleNotFoundException e) {
                response.status(404);
                LOG.warn("Not found article for delete Id: {} error {}", articleId, e.getMessage());
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
            }
                });
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
