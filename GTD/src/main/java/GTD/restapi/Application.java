package GTD.restapi;

import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Task;
import GTD.DL.hibernate.HibernateUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import GTD.restapi.web.SpringBootServletInitializer;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.json.*;

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
class GreetingController implements ApplicationContextAware {

	ApplicationContext ac;
	
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException
	{
		this.ac = ac;
	}

	
	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	String getAll(@PathVariable String name)
	{
		DAOTask dt = new DAOTask();
		dt.setSessionFactory(HibernateUtil.getSessionFactory());
		
		List<Task> tasks = dt.getAll();
		return name;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	String helloDefault()
	{

		DAOPerson daoPerson = new DAOPerson();
		daoPerson.setSessionFactory(HibernateUtil.getSessionFactory());
//		DAOState daoState = new DAOState();
//		daoState.setSessionFactory(HibernateUtil.getSessionFactory());  

		Person p = daoPerson.getOsoba("testLogin");
		if (p != null) {
			JsonArrayBuilder builder = Json.createArrayBuilder();

			JsonObject obj = Json.createObjectBuilder()
					.add("name", p.getJmeno())
					.add("id", p.getId())
					.build();
			builder.add(obj);


			return builder.build().toString();
		} else {
			return null;
		}

//		SessionFactory sf = (SessionFactory) ac.getBean(SessionFactory.class);
//		if (sf == null) return "nefunguje";
//		return "Hello, sf = " + sf.getClass().getSimpleName();
	}

//  @RequestMapping(method = RequestMethod.POST)
//  String defaultHello() {
//    return "Hello, default!";
//  }
}
