package org.dima.controller;

import org.dima.controller.request.ArticleCreateRequest;
import org.dima.controller.request.ArticleUpdateRequest;
import org.dima.controller.response.ErrorResponse;
import org.dima.entity.Article;
import org.dima.entity.Comment;
import org.dima.entity.id.ArticleId;
import org.dima.entity.id.CommentId;
import org.dima.repository.exception.ArticleNotFoundException;
import org.dima.service.ArticleService;
import org.dima.service.exception.ArticleCreateException;
import org.dima.service.exception.ArticleFindException;
import org.dima.service.exception.ArticleUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Response;
import spark.Request;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    private void updateArticle() {
        service.post("api/article/update/:articleId",
                (Request request, Response response) -> {
            response.type("application/json");
            ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
            ArticleUpdateRequest articleUpdateRequest = objectMapper.readValue(request.body(), ArticleUpdateRequest.class);
            try {
                List<Comment> commentList = new ArrayList<>();
                int comId = 0;
                for (String comment : articleUpdateRequest.comments()) {
                    commentList.add(new Comment(comment, articleId, new CommentId(comId)));
                    comId++;
                }
                articleService.update(
                        articleId,
                        articleUpdateRequest.name(),
                        articleUpdateRequest.tags(),
                        commentList
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
        service.get("api/article/:articleId",
            (Request request, Response response) -> {
            response.type("application/json");
            ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
            try {
                Article article = articleService.findById(articleId);
                Map<String, String> articleMap = new HashMap<>();
                articleMap.put("articleId", String.valueOf(article.getId().id()));
                articleMap.put("name", article.getName());
                articleMap.put("tags", article.getTags());
                if (article.getComments() != null) {
                    String strComments = "";
                    for (Comment comment : article.getComments()) {
                        strComments = strComments + comment.getComment() + " ";
                    }
                    articleMap.put("comments", strComments);
                }
                response.status(200);
                return objectMapper.writeValueAsString(articleMap);
            } catch (ArticleFindException e) {
                LOG.warn("Not found article Id: {} and exception {}", articleId, e.getMessage());
                response.status(404);
                return objectMapper.writeValueAsString(new ErrorResponse(e.getMessage()));
            }
            });
    }

    private void findAllArticle() {
        service.get("api/article/all",
                (Request request, Response response) -> {
            response.type("application/json");

            try {
                List<Article> articles = articleService.findAll();
                List<Map<String, String>> articlesList = new ArrayList<>();
                for (int i = 0; i < articles.size(); i++) {
                    articlesList.add(i, new HashMap<>());
                    articlesList.get(i).put("articleId", String.valueOf(articles.get(i).getId().id()));
                    articlesList.get(i).put("name", articles.get(i).getName());
                    articlesList.get(i).put("tags", articles.get(i).getTags());
                    System.out.println(articles.get(i).getComments());
                    if (articles.get(i).getComments() != null) {
                        String strComments = "";
                        for (Comment comment : articles.get(i).getComments()) {
                            strComments = strComments + comment.getComment() + " ";
                        }
                        articlesList.get(i).put("comments", strComments);
                    }
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
            ArticleId articleId = new ArticleId(Long.parseLong(request.params("articleId")));
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
