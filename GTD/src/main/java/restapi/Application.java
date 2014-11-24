package restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import restapi.web.SpringBootServletInitializer;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer { //

  public static void main(String[] args) {
    SpringApplication.run(applicationClass, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(applicationClass);
  }

  private static Class<Application> applicationClass = Application.class;
}

@RestController
@RequestMapping("/home")
class GreetingController {

  @RequestMapping(value = "/{name}", method = RequestMethod.GET)
  String hello(@PathVariable String name) {
    return "Hello, " + name + "!";
  }

  @RequestMapping(method = RequestMethod.GET)
  String helloDefault() {
    return "Hello, default!";
  }

//  @RequestMapping(method = RequestMethod.POST)
//  String defaultHello() {
//    return "Hello, default!";
//  }
}
