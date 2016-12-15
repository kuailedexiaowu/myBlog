package myBlog.shiro;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kj on 2016/10/26.
 */
@Service
public class UserinfoServiceImpl implements UserinfoService {
   @Resource
   UserinfoRepository userinfoRepository;
    @Override
    public Userinfo findByUname(String uname) {
        System.out.println("userinfoserviceimpl.findbyuname");
        return userinfoRepository.findByUname(uname);
    }
}
