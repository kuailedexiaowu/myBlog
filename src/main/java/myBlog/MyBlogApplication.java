package myBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * Created by kj on 2016/9/5.
 */
@EnableAspectJAutoProxy
@SpringBootApplication
@ComponentScan(basePackages = "myBlog")
public class MyBlogApplication  {

    public static void main(String[] args){
        SpringApplication.run(MyBlogApplication.class, args);
    }
}
