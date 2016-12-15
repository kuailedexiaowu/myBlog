package myBlog.controller;



import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.ir.annotations.Ignore;
import myBlog.domin.User;
import myBlog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by kj on 2016/9/5.
 */
@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController  {
    @Autowired
    Validator validator;
   @Resource
   private UserService userService;
    @RequestMapping("/check")
    public void  ckeckUser(HttpServletResponse response,HttpServletRequest request,
                           @RequestParam(value="username")String username, @RequestParam(value="password")String password) throws IOException {
        User user=userService.checkUser(username, password);
        if(user!=null)
        {   request.getSession().setAttribute("id",user.getId());
            response.sendRedirect("/blogowner.html");}
        else
        {response.sendRedirect("/index.html");}
    }
    @RequestMapping("/getname")
    public  String getUserName(HttpServletRequest request){
        int id=Integer.parseInt(request.getSession().getAttribute("id").toString());
        String username=userService.getName(id);
        return JSONObject.toJSONString(username);
    }
    @RequestMapping("/getnamebyid")
     public String getNameById( int id){
       String name= userService.getName(id);
        return name;
    }
    @RequestMapping("/getvisitbyid")
    public int getVisitById( int id){
        userService.addVisitById(id);
        int visit=userService.getVisitById(id);
        return visit;
    }
    @RequestMapping("/getuser")
    public User getUserById(HttpServletRequest request){
        int id=Integer.parseInt(request.getSession().getAttribute("id").toString());
        User user=userService.getUserById(id);
        return user;
    }
    @RequestMapping("/adduser")
    public void addUser(@Validated  User user, Errors errors, HttpServletResponse response) throws IOException {
        validator.validate(user,errors);
        userService.addUser(user);
        response.sendRedirect("/index.html");
    }
    @RequestMapping("/getidattribute")
    public String getIdAttribute(HttpSession session){
      int id= userService.getUserId(session);
        if(id==0)
            return "false";
        else
            return "true";


    }


}
