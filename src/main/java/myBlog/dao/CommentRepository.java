package myBlog.dao;

import myBlog.domin.Comment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kj on 2016/9/10.
 */
public interface CommentRepository extends CrudRepository<Comment,Long> {
}
