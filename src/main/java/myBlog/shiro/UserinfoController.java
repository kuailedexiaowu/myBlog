package myBlog.shiro;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kj on 2016/10/27.
 */
@Controller
public class UserinfoController {
    @RequestMapping("/userList")
    @RequiresPermissions("user:query")
    public String userInfo(){ return"userInfo"; }

    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo:add")//权限管理;
     public String userInfoAdd(){ return"userInfoAdd";
}
    @RequestMapping("/userDel")
    @RequiresPermissions("userInfo:del")//权限管理;
    public String userDel(){ return"userInfoDel";
}



}
