package myBlog.service;

import myBlog.dao.CommentRepository;
import myBlog.domin.Article;
import myBlog.domin.Comment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kj on 2016/9/10.
 */
@Service
public class CommentService {
    @Resource
    CommentRepository commentRepository;
    @Resource
    private JdbcTemplate jdbcTemplate;

    public void addComment(Comment comment){
        commentRepository.save(comment);
    }
    public List<Comment> getComments(int articleid){
        String sql="select username,context,time from comment where articleid=? order by time";
        RowMapper<Comment> rowMapper = new BeanPropertyRowMapper<Comment>(Comment.class);
        List<Comment> list=jdbcTemplate.query(sql,rowMapper,articleid);
        return list;
    }
}
