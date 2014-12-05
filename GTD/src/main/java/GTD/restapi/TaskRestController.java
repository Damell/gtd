/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.restapi;

import GTD.BL.BLAktivity.ActivitiyAdmin;
import GTD.BL.BLAktivity.ProjectAdmin;
import GTD.BL.BLAktivity.TaskAdmin;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLDAO.DAOActivity;
import GTD.DL.DLDAO.DAOException;
import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DAOProject;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLDAO.ItemNotFoundException;
import GTD.DL.DLEntity.Context;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.TaskState;
import GTD.DL.DLInterfaces.IDAOActivity;
import GTD.DL.DLInterfaces.IDAOPerson;
import GTD.DL.DLInterfaces.IDAOProject;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;




/**
 *
 * @author simon
 */
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskRestController
{
	
	
//	public static final String JSON_TASK_TITLE = "title";
//	public static final String JSON_TASK_DESCRIPTION = "description";
//	public static final String JSON_TASK_CREATOR = "creator";
//	// etc... 
//	// TODO steklsim ^ use these (or other) constants instead of strings
	
	private TaskAdmin taskAdmin;
	private PersonAdmin personAdmin;
	private ProjectAdmin projectAdmin;
	private DAOState daoState;

	public TaskRestController()
	{
		IDAOActivity da = new DAOActivity();
		IDAOTask dt = new DAOTask();
		IDAOPerson dp = new DAOPerson();
		IDAOProject dpr = new DAOProject();
		
		daoState = new DAOState();
		personAdmin = new PersonAdmin(dp, daoState);
		ActivitiyAdmin aa = new ActivitiyAdmin(da, daoState, personAdmin);
		taskAdmin = new TaskAdmin(dt, aa, personAdmin, daoState);
		projectAdmin = new ProjectAdmin(dpr, daoState, aa, personAdmin);
	}
	
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Task get(@PathVariable int id)
	{
		// TODO steklsim add authentication
		DAOTask daoTask = new DAOTask(); // TODO steklsim az bude autentizace bude se nacitat pres taskAdmin
		
		
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		
//		try {
//			Task t = daoTask.get(id);
//			HttpStatus status = t != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
//			return new ResponseEntity<>(getJSONFromTask(t).toString(), httpHeaders, status);
//		} catch (DAOException e) {
//			return new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		Task task = daoTask.get(id);
//		if (task == null) throw new ItemNotFoundException("Task with id '" + id + "' not found");
		return task;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable int id)
	{
		DAOTask dt = new DAOTask();
//		dt.setSessionFactory(HibernateUtil.getSessionFactory());
		
		
//		try {
			Task t = dt.get(id);
			if (t != null) dt.delete(t);
//			HttpStatus status = t != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
			return new ResponseEntity<>(null, null, HttpStatus.OK);
			
//		} catch (DAOException e) {
//			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Task> getAll()
	{
		DAOTask dt = new DAOTask();
//		dt.setSessionFactory(HibernateUtil.getSessionFactory());
		
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//		try {
//			List<Task> tasks = taskAdmin.getAllUkoly();
//
//			JsonArrayBuilder builder = Json.createArrayBuilder();
//			for (Task t : tasks) {
//				JsonObject obj = getJSONFromTask(t);
//				builder.add(obj);
//			}
//			return new ResponseEntity<>(builder.build().toString(), httpHeaders, HttpStatus.OK);
//		} catch (DAOException e) {
//			return new ResponseEntity<>(null, httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
		
		return dt.getAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Task create(@RequestBody Task task)
	{
//		try {
//			JsonObject json = getJsonObjectFromString(taskString);
//			
//			Task task = getTaskFromJSON(json);
			Person testUser = personAdmin.getOsoba(ApiConstants.TEST_USER_ID);
//			if (testUser == null) throw new ItemNotFoundException("User with id '" + ApiConstants.TEST_USER_ID + "' not found");
			populateTask(task, testUser);
			
			taskAdmin.addUkol(task, testUser, null); // TODO steklsim what about task's activity?
			
//			JsonObject taskJson = getJSONFromTask(task);
//			
//			HttpHeaders httpHeaders = new HttpHeaders();
//			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//			
//			return new ResponseEntity<>(taskJson.toString(), httpHeaders, HttpStatus.CREATED);
//		
//		} catch (DAOException de) {
//			if (de.getCause() instanceof ConstraintViolationException) { // TODO steklsim is this enough?
//				return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
//			} else {
//				return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//		} catch (JsonException je) {
//			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
//		}
		return task;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Task update(@PathVariable int id, @RequestBody Task task)
	{
//		try {
//			JsonObject json = getJsonObjectFromString(taskString);
//			
//			Person creator = personAdmin.getOsoba(json.getInt("creator"));
////			if (creator == null) return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
//			if (creator == null) throw new Item;
//			
//			Task dbTask = taskAdmin.getUkol(id, creator); // TODO steklsim change this when authentization is working
//			if (dbTask == null) return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
//			Task task = getTaskFromJSON(json, dbTask);
//			
//			taskAdmin.updateUkol(task, task.getCreator());
//			Task updatedTask = taskAdmin.getUkol(id, creator);
//			JsonObject taskJson = getJSONFromTask(updatedTask);
//			
//			HttpHeaders httpHeaders = new HttpHeaders();
//			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//			
//			return new ResponseEntity<>(taskJson.toString(), httpHeaders, HttpStatus.CREATED);
//		
//		} catch (DAOException de) {
//			if (de.getCause() instanceof ConstraintViolationException) { // TODO steklsim is this enough?
//				return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
//			} else {
//				return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//		} catch (JsonException je) {
//			return new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
//		}
		task.setId(id);
		Person testUser = personAdmin.getOsoba(ApiConstants.TEST_USER_ID);
		populateTask(task, testUser);
//		if (testUser == null) throw new ItemNotFoundException("User with id '" + ApiConstants.TEST_USER_ID + "' not found");
		taskAdmin.updateUkol(task, testUser);
		
		
		return taskAdmin.getUkol(id, testUser);
	}
	
	/**
	 * Replaces dummy properties from API with their real counterparts from database
	 * (where it's needed)
	 * @param task 
	 * @param user logged-in user 
	 */
	private void populateTask(Task task, Person user)
	{
		if (task.getOwner() != null) {
			Person owner = personAdmin.getOsoba(task.getOwner().getLogin());
			task.setOwner(owner);
		}
		if (task.getState() != null) {
			TaskState state = daoState.getTaskState(task.getState().getKod());
			task.setState(state);
		}
		// TODO steklsim resolve context
		
		if (task.getProject() != null) {
			Project project = projectAdmin.getProjekt(task.getProject().getId(), user);
			task.setProject(project);
		}
		
	}
}
