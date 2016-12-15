package myBlog.configuration;

import myBlog.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import javax.validation.ConstraintViolation;
import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;
import java.util.List;
import java.util.Set;

/**
 * Created by kj on 2016/10/20.
 */

@Configuration
public class ValidateConfiguration {

    @Autowired(required = false)
    MessageSource messageSource;
    @Bean
    public LocalValidatorFactoryBean validator(){
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean(){
            @Override
            public ExecutableValidator forExecutables() {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                return null;
            }

            @Override
            public ParameterNameProvider getParameterNameProvider() {
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                return null;
            }

            @Override
            protected void processConstraintViolations(Set<ConstraintViolation<Object>> violations, Errors errors) {
                if(!errors.hasFieldErrors()){
                    return;                }
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(processFieldErrorToJson(errors.getFieldErrors()));
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }

            private String processFieldErrorToJson(List<FieldError> fieldErrors){
                if(fieldErrors.size()>0){
                    StringBuilder sb = new StringBuilder("[");
                    for(int index=0;index<fieldErrors.size();index++){
                        if(index>0)
                            sb.append(",");
                        sb.append("{\"name\":").append("\"").append(fieldErrors.get(index).getField()).append("\",");
                        sb.append("\"errorMessage\":").append("\"").append(JsonUtil.formatStrToJson(fieldErrors.get(index).getDefaultMessage())).append("\"}");
                    }
                    sb.append("]");
                    return  sb.toString();
                }
                return "[]";
            }
        };
        localValidatorFactoryBean.setProviderClass(org.hibernate.validator.HibernateValidator.class);
        if (messageSource != null)
            localValidatorFactoryBean.setValidationMessageSource(messageSource);
        return localValidatorFactoryBean;
    }

    //开启方法参数校验
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

}

