package cn.cst.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//用于定义配置类，相当于applicationContext-mvc.xml文件；
// 定义一个拦截器，相当于之前的 mvc 里的配置
public class InterceptorConfig implements WebMvcConfigurer {

    //所要拦截的请求路径
    String[] addPathPatterns = {
            "/**"
    };
    //不需要拦截的请求路径
    String[] excludePathPatterns = {
            "/user/login","/user/error","/user/out"
    };
    //mvc:interceptor class=""
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
