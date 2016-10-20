package myBlog.dao;

import myBlog.domin.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by kj on 2016/9/5.
 */
public interface UserRepository extends CrudRepository<User,Long> {
}
