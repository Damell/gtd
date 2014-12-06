/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GTD.restapi;

/**
 *
 * @author skvarla
 */
import GTD.BL.BLAktivity.ActivitiyAdmin;
import GTD.BL.BLAktivity.ProjectAdmin;
import GTD.BL.BLAktivity.TaskAdmin;
import GTD.BL.BLOsoby.PersonAdmin;
import GTD.DL.DLDAO.DAOActivity;
import GTD.DL.DLDAO.DAOPerson;
import GTD.DL.DLDAO.DAOProject;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLDAO.DAOTask;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.ProjectState;
import GTD.DL.DLEntity.Task;
import GTD.DL.DLInterfaces.IDAOActivity;
import GTD.DL.DLInterfaces.IDAOPerson;
import GTD.DL.DLInterfaces.IDAOProject;
import GTD.DL.DLInterfaces.IDAOTask;
import GTD.DL.hibernate.HibernateUtil;
import java.math.BigDecimal;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectRestController 
{

	private PersonAdmin personAdmin;
	private ProjectAdmin projectAdmin;
	private DAOState daoState;
	
	public ProjectRestController()
	{
		IDAOActivity da = new DAOActivity();
		IDAOTask dt = new DAOTask();
		IDAOPerson dp = new DAOPerson();
		IDAOProject dpr = new DAOProject();
		
		daoState = new DAOState();
		personAdmin = new PersonAdmin(dp, daoState);
		ActivitiyAdmin aa = new ActivitiyAdmin(da, daoState, personAdmin);
//		taskAdmin = new TaskAdmin(dt, aa, personAdmin, daoState);
		projectAdmin = new ProjectAdmin(dpr, daoState, aa, personAdmin);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Project get(@PathVariable int id)
	{
		// TODO steklsim add authentication
		DAOProject daoProject = new DAOProject(); // TODO steklsim az bude autentizace bude se nacitat pres taskAdmin
		
		Project project = daoProject.get(id);
		return project;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Project> getAll()
	{
		DAOProject dp = new DAOProject();
		
		return dp.getAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable int id)
	{
		DAOProject daoProject = new DAOProject();
		
		Project p = daoProject.get(id);
		daoProject.delete(p);
		return new ResponseEntity<>(null, null, HttpStatus.OK);
			
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody Project prj)
	{
		Person testUser = personAdmin.getOsoba(ApiConstants.TEST_USER_ID);
//			if (testUser == null) throw new ItemNotFoundException("User with id '" + ApiConstants.TEST_USER_ID + "' not found");
		populateProject(prj, testUser);

		projectAdmin.addProjekt(prj, null, testUser); // TODO steklsim what about projects's activity?
			
		return prj;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody Project update(@PathVariable int id, @RequestBody Project prj)
	{
		prj.setId(id);
		Person testUser = personAdmin.getOsoba(ApiConstants.TEST_USER_ID);
		populateProject(prj, testUser);
		projectAdmin.updateProjekt(prj, testUser);
		
		
		return projectAdmin.getProjekt(id, testUser);
	}
	
	/**
	 * Replaces dummy properties from API with their real counterparts from database
	 * (where it's needed)
	 * @param prj 
	 * @param user logged-in user 
	 */
	private void populateProject(Project prj, Person user)
	{
		if (prj.getStav()!= null) {
			ProjectState state = daoState.getProjectState(prj.getStav().getKod());
			prj.setStav(state);
		}
		
		if (prj.getRodic()!= null) {
			Project project = projectAdmin.getProjekt(prj.getRodic().getId(), user);
			prj.setRodic(project);
		}
		if (prj.getOwner() != null) {
			Person person = personAdmin.getOsoba(prj.getOwner().getLogin());
			prj.setOwner(person);
		}
	}
	
}
