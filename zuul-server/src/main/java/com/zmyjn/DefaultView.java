package com.zmyjn;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/start/index.html");
        //registry.addViewController("/admin").setViewName("redirect:/admin/index.html");
//        registry.addViewController("/").setViewName("redirect:/index/index.html");
//        registry.addViewController("/admin/").setViewName("redirect:/admin/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/path/**").addResourceLocations("file:/html/");
        registry.addResourceHandler("/path/**").addResourceLocations("file:/html/");
    }
}
