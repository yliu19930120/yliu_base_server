package com.yliu.config;

import java.util.ArrayList;
import java.util.List;

import com.yliu.common.CrosInterceptor;
import com.yliu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yliu.common.WebFilter;
import com.yliu.common.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Autowired
	private UserService userService;

	@Value("${yliu.auth}")
	private boolean auth;
	@Value("${yliu.cross}")
	private boolean cross;
	//白名单
	private static final String[] AUTH_WHITELIST = {
			"/",
			// -- swagger ui
			"/swagger-resources/**",
			"/swagger-ui.html",
			"/v2/api-docs",
			"/webjars/**",
			"/login",
			"/error",
			"/logout"
	};

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if(cross){
			InterceptorRegistration crosRegistration = registry.addInterceptor(new CrosInterceptor());
			crosRegistration.addPathPatterns("/**");
		}
		if(auth){
			InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor(userService));
			registration.addPathPatterns("/**");
			registration.excludePathPatterns(AUTH_WHITELIST);       //添加不拦截路径
		}
	}

	/**
	 * 注册过滤器
	 * @return
	 */
//	 	@Bean
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

	/**
	 * 跨域支持
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowCredentials(true)
				.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
				.maxAge(3600L);
	}
}
