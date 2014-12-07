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
import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DAOProject;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLEntity.TaskState;
import GTD.DL.DLInterfaces.IDAOActivity;
import GTD.DL.DLInterfaces.IDAOPerson;
import GTD.DL.DLInterfaces.IDAOProject;
import GTD.DL.DLInterfaces.IDAOTask;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;


/**
 * @author simon
 */
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskRestController {

  private TaskAdmin taskAdmin;
  private PersonAdmin personAdmin;
  private ProjectAdmin projectAdmin;
  private DAOState daoState;

  protected Logger logger = LoggerFactory.getLogger(TaskRestController.class);

  public MessageSource getMessageSource() {
    return messageSource;
  }

  private MessageSource messageSource;

  @Autowired
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  public TaskRestController() {
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
  public
  @ResponseBody
  Task get(@PathVariable int id, ServletWebRequest wr) {
	  logRequest(wr);
    // TODO steklsim add authentication
    DAOTask daoTask = new DAOTask(); // TODO steklsim az bude autentizace bude se nacitat pres taskAdmin
    Task task = daoTask.get(id);
//		if (task == null) throw new ItemNotFoundException("Task with id '" + id + "' not found");
    return task;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> delete(@PathVariable int id, ServletWebRequest wr) {
	  logRequest(wr);
    DAOTask dt = new DAOTask();
    Task t = dt.get(id);
    if (t != null) dt.delete(t);
//			HttpStatus status = t != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(null, null, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET)
  public
  @ResponseBody
  List<Task> getAll(ServletWebRequest wr) {
	  
    logRequest(wr);
    DAOTask dt = new DAOTask();

    return dt.getAll();
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public Task create(@RequestBody Task task, ServletWebRequest wr) {
	  logRequest(wr);
    Person testUser = personAdmin.getOsoba(ApiConstants.TEST_USER_ID);
//			if (testUser == null) throw new ItemNotFoundException("User with id '" + ApiConstants.TEST_USER_ID + "' not found");
    populateTask(task, testUser);

    taskAdmin.addUkol(task, testUser, null); // TODO steklsim what about task's activity?

    return task;
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public
  @ResponseBody
  Task update(@PathVariable int id, @RequestBody Task task, ServletWebRequest wr) {
	  logRequest(wr);
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
   *
   * @param task
   * @param user logged-in user
   */
  private void populateTask(Task task, Person user) {
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
  
  private void logRequest(ServletWebRequest wr) 
  {
	  logger.debug(getMessageSource().getMessage("restApi.request.accepted", null, null), wr.getHttpMethod(), wr.getDescription(true));
  }
}
