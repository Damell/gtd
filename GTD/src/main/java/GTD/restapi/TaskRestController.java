/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.restapi;

import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Task;
import GTD.DL.hibernate.HibernateUtil;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




/**
 *
 * @author simon
 */
@RestController
@RequestMapping("/tasks")
public class TaskRestController
{
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	String getAll()
	{

		DAOTask dt = new DAOTask();
		dt.setSessionFactory(HibernateUtil.getSessionFactory());
		
		List<Task> tasks = dt.getAll();
		
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (Task t : tasks) {
			JsonObject project = Json.createObjectBuilder()
					.add("id", t.getProjekt().getId())
					.add("title", t.getProjekt().getNazev())
					.build();
			
			Interval interval = t.getKalendar();
			JsonObject calendar = null;
			if (interval != null) {
				 calendar = Json.createObjectBuilder()
						.add("id", interval.getId())
						.add("from", interval.getFrom().getTime())
						.add("to", interval.getTo().getTime())
						.build();
			}
			
			
			JsonObject obj = Json.createObjectBuilder()
					.add("id", t.getId())
					.add("title", t.getNazev())
					.add("description", t.getPopis())
					.add("owner", t.getVlastnik().getId())
					.add("creator", t.getTvurce().getId())
					.add("project", project)
					.add("calendar", calendar)
					.build();
			builder.add(obj);

		}

		return builder.build().toString();

//		SessionFactory sf = (SessionFactory) ac.getBean(SessionFactory.class);
//		if (sf == null) return "nefunguje";
//		return "Hello, sf = " + sf.getClass().getSimpleName();
	}
}
