package myBlog.dao;

import myBlog.domin.Article;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kj on 2016/9/7.
 */
public interface ArticleRepository extends CrudRepository<Article,Long> {
}
