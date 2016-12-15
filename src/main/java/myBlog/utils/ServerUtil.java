package myBlog.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kj on 2016/10/24.
 */
public class ServerUtil {
    private ServerUtil() {

    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

    }



    public static boolean isAjax() {
        return "XMLHttpRequest".equalsIgnoreCase(getHttpServletRequest().getHeader("X-Requested-With"));
    }





}
