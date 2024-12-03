package app.controller;

import app.Main;
import app.entity.Article;
import app.service.ArticleService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Service;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArticleFreemarkerController implements Controller {
    private final Service service;
    private final ArticleService articleService;
    private final FreeMarkerEngine freeMarkerEngine;

    public ArticleFreemarkerController(
            Service service,
            ArticleService articleService,
            FreeMarkerEngine freeMarkerEngine
    ) {
        this.service = service;
        this.articleService = articleService;
        this.freeMarkerEngine = freeMarkerEngine;
    }

    @Override
    public void initializeEndpoints() {
        getAllArticles();
    }

    private void getAllArticles() {
        service.get("/",
                (Request request, Response response) -> {
            response.type("text/html; charset=utf-8");
            List<Article> articles = articleService.findAll();
            List<Map<String, String>>  articlesListMap =
                    articles.stream()
                            .map(Article -> Map.of
                                    (
                                    "name", Article.getName(),
                                    "tags", Article.getTags(),
                                    "comments", Integer.toString(Article.getComments().size())
                                    )
                            ).toList();
            Map<String, Object> model = new HashMap<>();
            model.put("articles", articlesListMap);
            return freeMarkerEngine.render(new ModelAndView(model, "index.ftl"));
                });
    }
}
