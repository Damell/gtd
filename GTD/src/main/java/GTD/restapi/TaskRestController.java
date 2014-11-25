/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.restapi;

import GTD.DL.DLDAO.DAOException;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.TaskState;
import GTD.DL.hibernate.HibernateUtil;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




/**
 *
 * @author simon
 */
@RestController
@RequestMapping("/tasks")
public class TaskRestController
{
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable int id)
	{
		DAOTask dt = new DAOTask();
		dt.setSessionFactory(HibernateUtil.getSessionFactory());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		try {
			Task t = dt.get(id);
			HttpStatus status = t != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
			return new ResponseEntity<>(getTaskJSON(t).toString(), httpHeaders, status);
		} catch (DAOException e) {
			return new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll()
	{
		DAOTask dt = new DAOTask();
		dt.setSessionFactory(HibernateUtil.getSessionFactory());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		try {
			List<Task> tasks = dt.getAll();

			JsonArrayBuilder builder = Json.createArrayBuilder();
			for (Task t : tasks) {
				JsonObject obj = getTaskJSON(t);
				builder.add(obj);
			}
			return new ResponseEntity<>(builder.build().toString(), httpHeaders, HttpStatus.OK);
		} catch (DAOException e) {
			return new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}


//		SessionFactory sf = (SessionFactory) ac.getBean(SessionFactory.class);
//		if (sf == null) return "nefunguje";
//		return "Hello, sf = " + sf.getClass().getSimpleName();
	}
	
	private JsonObject getTaskJSON(Task t)
	{
		if (t == null) return Json.createObjectBuilder().build();
		
			
		
		TaskState ts = t.getStav();
		JsonObject state = Json.createObjectBuilder()
				.add("id", ts.getId())
				.add("code", ts.getKod())
				.add("title", ts.getNazev())
				.add("description", ts.getPopis())
				.build()
		;

		
		JsonObjectBuilder obj = Json.createObjectBuilder()
				.add("id", t.getId())
				.add("title", t.getNazev())
				.add("description", t.getPopis())
				.add("owner", t.getVlastnik().getId())
				.add("creator", t.getTvurce().getId())
				.add("state", state)
		;

		if (t.getProjekt() != null) {
			JsonObject project = Json.createObjectBuilder()
						.add("id", t.getProjekt().getId())
						.add("title", t.getProjekt().getNazev())
						.build()
			;
			obj.add("project", project);
		}
		
		Context ctx = t.getKontext();
		if (ctx != null) {
			JsonObject context = Json.createObjectBuilder()
					.add("id", ctx.getId())
					.add("title", ctx.getNazev())
					.build()
			;
			obj.add("context", context);
		}
		
		Interval interval = t.getKalendar();
		if (interval != null) {
			JsonObject calendar = Json.createObjectBuilder()
					.add("id", interval.getId())
					.add("from", interval.getFrom().getTime())
					.add("to", interval.getTo().getTime())
					.build()
			;
			obj.add("calendar", calendar);
		}
		
		return obj.build();
			
	}
	
}
