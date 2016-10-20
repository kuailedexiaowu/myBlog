package myBlog.controller;

import myBlog.domin.Comment;
import myBlog.service.CommentService;
import myBlog.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * Created by kj on 2016/9/10.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    CommentService commentService;
    @Resource
    UserService userService;
    @RequestMapping("/addcomment")
    public void addComment(String context, String articleid, HttpSession  session) throws IOException {
       //System.out.print(articleid);
        Comment comment=new Comment();
        Object obj=session.getAttribute("id");
        if(null==obj){
            comment.setUsername("游客");comment.setUserid(0);}
        else{
            int id=Integer.parseInt(obj.toString());
            comment.setUserid(id);
            comment.setUsername(userService.getName(id));}
            comment.setContext(context);
            comment.setArticleid(Integer.parseInt(articleid));
            comment.setTime(new Date(System.currentTimeMillis()));
            commentService.addComment(comment);

    }
    @RequestMapping("/getcomments")
    public List getComments(int articleid){
        List<Comment> list=commentService.getComments(articleid);
        return list;
    }
}
