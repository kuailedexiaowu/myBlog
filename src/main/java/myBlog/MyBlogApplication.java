package myBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Created by kj on 2016/9/5.
 */
@SpringBootApplication
@ComponentScan(basePackages = "myBlog")
public class MyBlogApplication {


    public static void main(String[] args){
        SpringApplication.run(MyBlogApplication.class, args);

    }
}
