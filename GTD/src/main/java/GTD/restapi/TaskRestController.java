/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.restapi;

import GTD.BL.BLAktivity.ActivitiyAdmin;
import GTD.BL.BLAktivity.TaskAdmin;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLDAO.DAOActivity;
import GTD.DL.DLDAO.DAOException;
import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DAOProject;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.TaskState;
import GTD.DL.DLInterfaces.IDAOActivity;
import GTD.DL.DLInterfaces.IDAOPerson;
import GTD.DL.DLInterfaces.IDAOState;
import GTD.DL.DLInterfaces.IDAOTask;
import GTD.DL.hibernate.HibernateUtil;
import java.io.StringReader;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import org.hibernate.exception.ConstraintViolationException;
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
@RequestMapping("/api/v1/tasks")
public class TaskRestController
{
	public static final String JSON_TASK_TITLE = "title";
	public static final String JSON_TASK_DESCRIPTION = "description";
	public static final String JSON_TASK_CREATOR = "creator";
	// etc... 
	// TODO steklsim ^ use these (or other) constants instead of strings
	
	private TaskAdmin taskAdmin;
	private PersonAdmin personAdmin;

	public TaskRestController()
	{
		IDAOActivity da = new DAOActivity();
		IDAOTask dt = new DAOTask();
		IDAOState ds = new DAOState();
		IDAOPerson dp = new DAOPerson();
		
		personAdmin = new PersonAdmin(dp, ds);
		ActivitiyAdmin aa = new ActivitiyAdmin(da, ds, personAdmin);
		taskAdmin = new TaskAdmin(dt, aa, personAdmin, ds);
	}
	
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable int id)
	{
		// TODO steklsim add authentication
		DAOTask daoTask = new DAOTask();
//		daoTask.setSessionFactory(HibernateUtil.getSessionFactory());
		
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		try {
			Task t = daoTask.get(id);
			HttpStatus status = t != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
			return new ResponseEntity<>(getJSONFromTask(t).toString(), httpHeaders, status);
		} catch (DAOException e) {
			return new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable int id)
	{
		DAOTask dt = new DAOTask();
//		dt.setSessionFactory(HibernateUtil.getSessionFactory());
		
		
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
//		DAOTask dt = new DAOTask();
//		dt.setSessionFactory(HibernateUtil.getSessionFactory());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		try {
			List<Task> tasks = taskAdmin.getAllUkoly();

			JsonArrayBuilder builder = Json.createArrayBuilder();
			for (Task t : tasks) {
				JsonObject obj = getJSONFromTask(t);
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
		try {
			JsonObject json = getJsonObjectFromString(taskString);
			
			Task task = getTaskFromJSON(json);
			Person creator = task.getTvurce();
			
			taskAdmin.addUkol(task, creator, null); // TODO steklsim what about task's activity?
			
			JsonObject taskJson = getJSONFromTask(task);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			return new ResponseEntity<>(taskJson.toString(), httpHeaders, HttpStatus.CREATED);
		
		} catch (DAOException de) {
			if (de.getCause() instanceof ConstraintViolationException) { // TODO steklsim is this enough?
				return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (JsonException je) {
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody String taskString)
	{
		try {
			JsonObject json = getJsonObjectFromString(taskString);
			
			Person creator = personAdmin.getOsoba(json.getInt("creator"));
			if (creator == null) return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
			
			Task dbTask = taskAdmin.getUkol(id, creator); // TODO steklsim change this when authentization is working
			if (dbTask == null) return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
			Task task = getTaskFromJSON(json, dbTask);
			
			taskAdmin.updateUkol(task, task.getTvurce());
			Task updatedTask = taskAdmin.getUkol(id, creator);
			JsonObject taskJson = getJSONFromTask(updatedTask);
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			return new ResponseEntity<>(taskJson.toString(), httpHeaders, HttpStatus.CREATED);
		
		} catch (DAOException de) {
			if (de.getCause() instanceof ConstraintViolationException) { // TODO steklsim is this enough?
				return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (JsonException je) {
			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	/**
	 * Creates a JsonObject instance from given Task instance
	 * @param t
	 * @return 
	 */
	private JsonObject getJSONFromTask(Task t)
	{
		if (t == null) return Json.createObjectBuilder().build();
		
			
		
//		System.out.println("STATE");
		TaskState ts = t.getStav();
		JsonObject state = Json.createObjectBuilder()
				.add("id", ts.getId())
				.add("code", ts.getKod())
				.add("title", ts.getNazev())
				.add("description", ts.getPopis())
				.build()
		;

//		System.out.println("BASIC");
		JsonObjectBuilder obj = Json.createObjectBuilder()
				.add("id", t.getId())
				.add("title", t.getNazev())
				
				.add("owner", t.getVlastnik().getId())
				.add("creator", t.getTvurce().getId())
				.add("state", state)
		;
//		System.out.println("DESCRIPTION");
		if (t.getPopis() != null) {
			obj.add("description", t.getPopis());
		}
//		System.out.println("PROJECT");
		if (t.getProjekt() != null) {
			JsonObject project = Json.createObjectBuilder()
						.add("id", t.getProjekt().getId())
						.add("title", t.getProjekt().getNazev())
						.build()
			;
			obj.add("project", project);
		}
//		System.out.println("CONTEXT");
		Context ctx = t.getKontext();
		if (ctx != null) {
			JsonObject context = Json.createObjectBuilder()
					.add("id", ctx.getId())
					.add("title", ctx.getNazev())
					.build()
			;
			obj.add("context", context);
		}
//		System.out.println("CALENDAR");
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
	
	private JsonObject getJsonObjectFromString(String jsonString)
	{
		JsonReader jr = Json.createReader(new StringReader(jsonString));
		return jr.readObject();
	}
	
	/**
	 * Creates a Task instace from JsonObject instance. ONLY sets fields that are important
	 * when persisting new task or updating it in database (mostly IDs)!
	 * @param tj 
	 * @param updatedTask if specified, this task is updated instead of creating a new one
	 * @return 
	 */
	private Task getTaskFromJSON(JsonObject tj, Task updatedTask)
	{
//		JsonReader jr = Json.createReader(new StringReader(taskJson));
//		JsonObject tj = jr.readObject();
		
		boolean isUpdate = updatedTask != null;
		Task task = updatedTask != null ? updatedTask : new Task();
		
		if (isUpdate) {
			if (tj.containsKey("owner")) {							// owner
				Person owner = new Person();
				owner.setId(tj.getInt("owner"));
				task.setVlastnik(owner);
			}
			if (tj.containsKey("state")) {							// state
				TaskState state = new TaskState();
				state.setId(tj.getJsonObject("state").getInt("id"));
				task.setStav(state);
			}
			if (tj.containsKey("title")) {							// title (update)
				task.setNazev(tj.getString("title"));
			}
			
		} else {
			task.setNazev(tj.getString("title"));					// title (new)
			
			Person creator = new Person();							// creator
			creator.setId(tj.getInt("creator"));
			task.setTvurce(creator);
		}
//		Project project = null;
		if (tj.containsKey("project")) {							// project
			Project project = new Project();
			project.setId(tj.getJsonObject("project").getInt("id"));
			task.setProjekt(project);
		}
		
//		TaskState state = new TaskState();
//		state.setId(tj.getJsonObject("state").getInt("id"));
//		Context context = null;
		if (tj.containsKey("context")) {							// context
			Context context = new Context();
			context.setId(tj.getJsonObject("context").getInt("id"));
		}
//		Interval calendar = null;
		if (tj.containsKey("calendar")) {							// calendar
			Interval calendar = new Interval();
			calendar.setFrom(new Date(tj.getJsonObject("calendar").getString("from")));
			calendar.setTo(new Date(tj.getJsonObject("calendar").getString("to")));
			task.setKalendar(calendar);
		}
		if (tj.containsKey("description")) {
			task.setPopis(tj.getString("description"));				// description
		} 

//		Task task = new Task();
//		task.setNazev(tj.getString("title"));
//		task.setVlastnik(owner);
//		task.setTvurce(creator);
//		task.setStav(state);
//		if (project != null) task.setProjekt(project);
//		if (calendar != null) task.setKalendar(calendar);
//		if (context != null) task.setKontext(context);
		
		return task;
	}

	
	private Task getTaskFromJSON(JsonObject taskJson)
	{
		return getTaskFromJSON(taskJson, null);
	}
	
}
