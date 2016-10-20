package myBlog.service;

import myBlog.dao.UserRepository;
import myBlog.domin.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by kj on 2016/9/5.
 */
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private JdbcTemplate jdbcTemplate;
    public User  checkUser(String username,String password) {
        User user=null;
        String sql="select * from user where username=? and password=?";

        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        List<User> list=jdbcTemplate.query(sql,rowMapper,username,password);
       if(list.size()!=0)
       {user=list.get(0);}
       // User user= jdbcTemplate.queryForObject(sql,rowMapper,username,password);
        return user;
    }
    public String getName(int id){
        String sql="select username from user where id=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        List<User> list=jdbcTemplate.query(sql,rowMapper,id);
        User user=list.get(0);
        return user.getUsername();
    }
     public int getUserId(HttpSession session){
         int id=Integer.parseInt(session.getAttribute("id").toString());
         return id;
     }
     public User getUserById(int id){
         String sql="select * from user where id=?";
         RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
         List<User> list=jdbcTemplate.query(sql,rowMapper,id);
         User user=list.get(0);
         return user;
     }
    public int getVisitById(int id){
        String sql="select visitor from user where id=?";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        List<User> list=jdbcTemplate.query(sql,rowMapper,id);
        User user=list.get(0);
        return user.getVisitor();

    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public void addVisitById(int id){
        String sql="update user set visitor=visitor+1 where id=?";
       jdbcTemplate.update(sql,id);

    }

}
