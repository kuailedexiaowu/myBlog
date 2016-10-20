package myBlog.service;

import myBlog.dao.ArticleRepository;
import myBlog.domin.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by kj on 2016/9/7.
 */
@Service
public class ArticleService {
    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Resource
    UserService userService;
    public void saveArticle(Article article, HttpSession session, HttpServletResponse response) throws IOException {
      int writerId=Integer.parseInt(session.getAttribute("id").toString());
        article.setWriterid(writerId);
        article.setPraise(0);
        article.setTime(new Date(System.currentTimeMillis()));
        articleRepository.save(article);
        response.sendRedirect("/blogowner.html");
    }
    public Article getLastArticle(HttpSession session){
        Article article=null;
        int id=userService.getUserId(session);
        String  sql="select * from article where writerid=? order by id desc";
        RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        List<Article> list=jdbcTemplate.query(sql,rowMapper,id);
        if(list.size()!=0)
         article=list.get(0);
        return article;
    }
    public List getArticlesByType(String type){
        String sql="select id,writerid,title ,content from article where type=? order by time desc";
        RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        List<Article> list=jdbcTemplate.query(sql,rowMapper,type);
        return list;
    }
    public List getHotArticle(){
        String sql="select * from article order by praise desc";
        RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        List<Article> list=jdbcTemplate.query(sql,rowMapper);
        return list;
    }
    public Article getArticleById( int id){

        String sql="select * from article where id=?";
        RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        List<Article> list=jdbcTemplate.query(sql,rowMapper,id);
        Article article=list.get(0);
        return article;

    }
    public List getArticles(int id){
        String sql="select * from articles where id=?";
        RowMapper<Article> rowMapper = new BeanPropertyRowMapper<Article>(Article.class);
        List<Article> list=jdbcTemplate.query(sql,rowMapper,id);
        return list;
    }
}
