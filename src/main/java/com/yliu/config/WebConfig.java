package com.yliu.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yliu.common.WebFilter;
import com.yliu.common.WebInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	
	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		InterceptorRegistration registration = registry.addInterceptor(new WebInterceptor());
		registration.addPathPatterns("/**");
//		 registration.excludePathPatterns("/","/login","/error","/static/**","/logout");       //添加不拦截路径
	}
	
	/**
	 * 注册过滤器
	 * @return
	 */
	 	@Bean
	    public FilterRegistrationBean timeFilter(){
	        //创建 过滤器注册bean
	        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
	      
	        WebFilter filter = new WebFilter();
	        
	        registrationBean.setFilter(filter);

	        List<String> urls = new ArrayList<>();
	        urls.add("/*");   //给所有请求加过滤器
	        //设置 有效url
	        registrationBean.setUrlPatterns(urls);

	        return registrationBean;
	    }
	 	
}
