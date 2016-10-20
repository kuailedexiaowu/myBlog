package myBlog.controller;

import myBlog.domin.Article;
import myBlog.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by kj on 2016/9/7.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
   @Resource
    ArticleService articleService;
    @RequestMapping("/savearticle")
    public void saveArticle(Article article, HttpServletRequest request, HttpServletResponse response) throws IOException {
     articleService.saveArticle(article,request.getSession(),response);
 }
    @RequestMapping("/getLastArticle")
    public Article getLastArticle(HttpSession session){
      Article article=articleService.getLastArticle(session);
     return article;
 }
    @RequestMapping("/getarticlesbytype")
    public List getArticlesByType(String type){
        List list=articleService.getArticlesByType(type);
        return list;
    }
    @RequestMapping("/gethotarticle")
    public List getHotArticle(){
        List list=articleService.getHotArticle();
        return  list;

    }
    @RequestMapping("/getarticlebyid")
    public Article getArticleById(int id){
        Article article=articleService.getArticleById(id);
        return article;
    }
    @RequestMapping("/getarticles")
    public List getArticles(int id){
       List list=articleService.getArticles(id);
        return list;
    }
}
