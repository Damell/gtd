/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.restapi;

import GTD.DL.DLDAO.DAOException;
import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DAOProject;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.TaskState;
import GTD.DL.hibernate.HibernateUtil;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public static final String JSON_TASK_TITLE = "title";
	public static final String JSON_TASK_DESCRIPTION = "description";
	public static final String JSON_TASK_CREATOR = "creator";
	// etc... 
	// TODO steklsim ^ use these (or other) constants instead of strings
	
	
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
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable int id)
	{
		DAOTask dt = new DAOTask();
		dt.setSessionFactory(HibernateUtil.getSessionFactory());
		
		
		try {
			Task t = dt.get(id);
			if (t != null) dt.delete(t);
			HttpStatus status = t != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
			return new ResponseEntity<>(null, null, status);
			
		} catch (DAOException e) {
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
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
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody String taskString)
	{
		DAOTask dt = new DAOTask();
		dt.setSessionFactory(HibernateUtil.getSessionFactory());
//		DAOPerson dp = new DAOPerson();
//		dp.setSessionFactory(HibernateUtil.getSessionFactory());
//		DAOProject dpr = new DAOProject();
//		dpr.setSessionFactory(HibernateUtil.getSessionFactory());
		
		JsonReader jr = Json.createReader(new StringReader(taskString));
		JsonObject tj = jr.readObject();
		
//		if (!checkJsonTask(tj)) {
//			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
//		}
		try {
			Person owner = new Person();
			owner.setId(tj.getInt("owner"));
			Person creator = new Person();
			creator.setId(tj.getInt("creator"));
			Project project = null;
			if (tj.containsKey("project")) {
				project = new Project();
				project.setId(tj.getJsonObject("project").getInt("id"));
			}
			TaskState state = new TaskState();
			state.setId(tj.getJsonObject("state").getInt("id"));
			Context context = null;
			if (tj.containsKey("context")) {
				context = new Context();
				context.setId(tj.getJsonObject("context").getInt("id"));
			}
			Interval calendar = null;
			if (tj.containsKey("calendar")) {
				calendar = new Interval();
				calendar.setFrom(new Date(tj.getJsonObject("calendar").getString("from")));
				calendar.setTo(new Date(tj.getJsonObject("calendar").getString("to")));
			}
			
			Task task = new Task();
			task.setNazev(tj.getString("title"));
			task.setVlastnik(owner);
			task.setTvurce(creator);
			task.setStav(state);
			if (tj.containsKey("description")) task.setPopis(tj.getString("description"));
			if (project != null) task.setProjekt(project);
			if (calendar != null) task.setKalendar(calendar);
			if (context != null) task.setKontext(context);
			
			dt.create(task);
			
			return new ResponseEntity<>(null, null, HttpStatus.CREATED);
		
		} catch (DAOException de) {
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
			// TODO steklsim it can also be BAD_REQUEST
		} catch (NullPointerException npe) {
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		}
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
				
				.add("owner", t.getVlastnik().getId())
				.add("creator", t.getTvurce().getId())
				.add("state", state)
		;
		
		if (t.getPopis() != null) {
			obj.add("description", t.getPopis());
		}

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

	private boolean checkJsonTask(JsonObject tj)
	{
		return true; // TODO steklsim checkJsonTask()
	}
	
}
