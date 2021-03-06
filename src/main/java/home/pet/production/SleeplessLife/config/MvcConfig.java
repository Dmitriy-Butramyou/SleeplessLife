package home.pet.production.SleeplessLife.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Mvc котфигурация
 *
 * @author D.Butramyou
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Value("${upload.path}")
  private String uploadPath;

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/img/**")
        .addResourceLocations("file:/" + uploadPath + "/");
    registry.addResourceHandler("/style/**")
        .addResourceLocations("classpath:/style/");
    registry.addResourceHandler("/javaScript/**")
        .addResourceLocations("classpath:/javaScript/");
  }
}
