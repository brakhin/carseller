package ru.bgbrakhi.carseller.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/cars").setViewName("cars");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/mynotes").setViewName("mynotes");
        registry.addViewController("/403").setViewName("403");
    }
}
