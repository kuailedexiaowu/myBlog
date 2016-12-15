package myBlog.shiro;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kj on 2016/10/26.
 */
@Repository
public interface UserinfoRepository extends CrudRepository<Userinfo,Long> {
    public Userinfo findByUname(String uname);
}
