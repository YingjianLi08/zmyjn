package com.zmyjn;

//import com.zmyjn.filter.TokenFilter;
import com.zmyjn.filter.PreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 微服务网关
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableZuulProxy // 开启网关
@EnableEurekaClient //向服务中心注册
public class ZuulApplication {

	@Bean
	@LoadBalanced //开启ribbon负载均衡
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}


	/**
	 * 配置过滤器
	 * @return
	 */
//	@Bean
//	public FilterRegistrationBean jwtFilter() {
//		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		registrationBean.setFilter(new JwtFilter());
//		//添加需要拦截的url
//		List<String> urlPatterns = new ArrayList<>();
//		urlPatterns.add("/*");
//		registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
//		registrationBean.setOrder(2);
//		return registrationBean;
//	}

//	@Bean
//	public FilterRegistrationBean jwtFilter() {
//		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		registrationBean.setFilter(new TokenFilter());
//		//添加需要拦截的url
//		List<String> urlPatterns = new ArrayList<>();
//		urlPatterns.add("/*");
//		registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
//		registrationBean.setOrder(2);
//		return registrationBean;
//	}

//	@Bean
//	public TokenFilter tokenFilter(){
//		return new TokenFilter();
//	}

//	@Bean
//	public PreFilter preFilter(){
//		return new PreFilter();
//	}
}
