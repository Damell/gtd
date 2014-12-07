package GTD.restapi.config;

import GTD.restapi.util.RequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import GTD.restapi.web.SpringBootServletInitializer;

import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.Filter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ApplicationConfig extends SpringBootServletInitializer { //

  public static void main(String[] args) {
    SpringApplication.run(applicationClass, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(applicationClass);
  }

  @Bean
     public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages/messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public HandlerInterceptor requestInterceptor() {
    return new RequestInterceptor();
  }

  private static Class<ApplicationConfig> applicationClass = ApplicationConfig.class;
}

//@RestController
//@RequestMapping("/home")
//class GreetingController implements ApplicationContextAware {
//
//	ApplicationContext ac;
//
//	@Override
//	public void setApplicationContext(ApplicationContext ac) throws BeansException
//	{
//		this.ac = ac;
//	}
//
//
//
//	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
//	String getAll(@PathVariable String name)
//	{
//		DAOTask dt = new DAOTask();
//		dt.setSessionFactory(HibernateUtil.getSessionFactory());
//
//		List<Task> tasks = dt.getAll();
//		return name;
//	}
//
//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	String helloDefault()
//	{
//
//		DAOPerson daoPerson = new DAOPerson();
//		daoPerson.setSessionFactory(HibernateUtil.getSessionFactory());
////		DAOState daoState = new DAOState();
////		daoState.setSessionFactory(HibernateUtil.getSessionFactory());
//
//		Person p = daoPerson.getOsoba("testLogin");
//		if (p != null) {
//			JsonArrayBuilder builder = Json.createArrayBuilder();
//
//			JsonObject obj = Json.createObjectBuilder()
//					.add("name", p.getJmeno())
//					.add("id", p.getId())
//					.build();
//			builder.add(obj);
//
//
//			return builder.build().toString();
//		} else {
//			return null;
//		}
//
////		SessionFactory sf = (SessionFactory) ac.getBean(SessionFactory.class);
////		if (sf == null) return "nefunguje";
////		return "Hello, sf = " + sf.getClass().getSimpleName();
//	}
//}
