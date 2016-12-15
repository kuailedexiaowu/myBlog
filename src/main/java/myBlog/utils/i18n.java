package myBlog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;

/**
 * Created by kj on 2016/10/24.
 */
@Component
public class I18n {

    private MessageSource messageSource;
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public  String getMessage(String code, Object[] args, String defaultMessage  ){
        return  messageSource.getMessage(code,args,defaultMessage,getLocale());
    }
    public String getMessage(String code,Locale locale){
        return messageSource.getMessage(code,null,locale);
    }


    public  String getMessage(String code, Object[] args  ){
        return  messageSource.getMessage(code,args,getLocale());
    }

    public  String getMessage(String code ){
        return  messageSource.getMessage(code,null,getLocale());
    }

    private Locale getLocale(){
        String locale =null;
        try{
            locale =  ServerUtil.getHttpServletRequest().getParameter("locale");
        }catch (Exception e){
        }
        if(locale == null){
            return  null;
        }
        String [] tmp    = locale.split("_");
        return new Locale(tmp[0],tmp[1]);
    }

}
