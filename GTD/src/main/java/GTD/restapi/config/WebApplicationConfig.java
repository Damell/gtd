package GTD.restapi.config;

import GTD.restapi.util.RequestInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Drugnanov on 7.12.2014.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="GTD.restapi")
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new RequestInterceptor());
  }

}