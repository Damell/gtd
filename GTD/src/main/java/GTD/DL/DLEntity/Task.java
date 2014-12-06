package GTD.DL.DLEntity;

import GTD.restapi.DateSerializer;
import GTD.restapi.PersonDeserializer;
import GTD.restapi.PersonSerializer;
import GTD.restapi.ProjectDeserializer;
import GTD.restapi.ProjectSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Třída predstavující úkol - realizovatelnou akci.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:55
 */
@Entity
@Table(name = "task")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task extends Action {

	/**
	 * Projekt úkolu.
	 */
	@ManyToOne
	@JoinColumn(name = "projekt_id")
	@JsonSerialize(using = ProjectSerializer.class)
	@JsonDeserialize(using = ProjectDeserializer.class)
	private Project project;
	/**
	 * Tvůrce úkolu (může se lišit od vlastníka - což je přiřazená osoba)
	 */
	@ManyToOne
	@JoinColumn(name = "tvurce_id", nullable = false)
	@JsonSerialize(using = PersonSerializer.class)
	@JsonDeserialize(using = PersonDeserializer.class)
	private Person creator;
	/**
	 * Záznam o úkolu v kalendáři.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "kalendar_id")
	@JsonSerialize(using = DateSerializer.class) 
	private Interval calendar;
	/**
	 * Context úkolu.
	 */
	@ManyToOne
	@JoinColumn(name = "kontext_id")
	@JsonIgnore
	private Context context;

	/**
	 * Stav úkolu
	 */
	@ManyToOne
	@JoinColumn(name = "stav_id", nullable = false)
	private TaskState state;


	public void finalize() throws Throwable {
		super.finalize();
	}

	public Task(String nazev, String popis, Person vlastnik, Project projekt, Person tvurce, TaskState stav)
	{
		super(nazev, popis, vlastnik);
		this.project = projekt;
		this.creator = tvurce;
		this.state = stav;
	}



	/**
	 * Konstruktor ukolu
	 */
	public Task(){

	}

	/**
	 * Vrati kalendat ukolu
	 * @return kalendar
	 */
	public Interval getCalendar(){
		return calendar;
	}

	/**
	 * Vrati kontext ukolu
	 * @return kontext
	 */
	public Context getContext(){
		return context;
	}

	/**
	 * Vrati projekt úkolu
	 * @return projekt
	 */
	public Project getProject(){
		return project;
	}

	/**
	 * Vrati tvurce úkolu
	 * @return tvurce
	 */
	public Person getCreator(){
		return creator;
	}

	public void setProject(Project project)
	{
		this.project = project;
	}

	public void setCreator(Person creator)
	{
		this.creator = creator;
	}

	public void setCalendar(Interval calendar)
	{
		this.calendar = calendar;
	}

	public void setContext(Context context)
	{
		// TODO steklsim vyjimka pokud vlastnik ukolu != vlastnik kontextu 
		this.context = context;
	}

	public TaskState getState()
	{
		return state;
	}

	public void setState(TaskState state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		return "Ukol: id=" + this.getId() + ", nazev=" + this.getTitle();
	}

	@Override
	public int hashCode()
	{
		int hash = 5;
		hash = 41 * hash + Objects.hashCode(this.calendar);
		hash = 41 * hash + Objects.hashCode(this.context);
		hash = 41 * hash + Objects.hashCode(this.state);
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!super.equals(obj)) return false;
		
		final Task other = (Task) obj;
		if (!Objects.equals(this.project, other.project)) {
			return false;
		}
		if (!Objects.equals(this.creator, other.creator)) {
			return false;
		}
		if (!Objects.equals(this.calendar, other.calendar)) {
			return false;
		}
		if (!Objects.equals(this.context, other.context)) {
			return false;
		}
		if (!Objects.equals(this.state, other.state)) {
			return false;
		}
		return true;
	}

	/**
	 * Updates this task based on not-null properties of given task
	 * @param task 
	 */
	@Override
	public void update(Action task)
	{
		super.update(task);
		Task t = (Task) task;
		if (t.getProject() != null) setProject(t.getProject());
		if (t.getCalendar() != null) setCalendar(t.getCalendar());
		if (t.getContext() != null) setContext(t.getContext());
		if (t.getState()!= null) setState(t.getState());
	}

}