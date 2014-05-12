/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GTD.PL.PLView;

/**
 *
 * @author damell
 */
public class Consts {

	public static final String APP_TITLE = "Getting Things Done";

	// Login panel
	public static final String USERNAME = "Uživatelské jméno: ";
	public static final String PASSWORD = "Heslo: ";
	public static final String LOGIN = "Přihlásit!";
	public static final String LOGIN_ERROR = "Neplatné uživatelské jméno nebo heslo. Prosím zkuste to znovu";

	// Tabs
	public static final String ACTIVITES = "Činnosti";
	public static final String TASKS_AND_PROJECTS = "Úkoly a projekty";
	public static final String PROJECTS = "Projekty";
	public static final String MY_TASKS = "Moje úkoly";
	
	// Activities tab
	public static final String ADD_ACTIVITY = "Přidat činnost";
	public static final String PROCESS_ACTIVITY = "Zpracovat činnost";
	public static final String DELETE_ACTIVITY = "Smazat činnost";
	public static final String NO_ACTIVITY_SELECTED = "Není zvolena žádná činnost";
	
	public static final String TITLE = "Název";
	public static final String DESC = "Popis";
	public static final String TITLE_EMPTY = "Chybí název";
	public static final String DESC_EMPTY = "Chybí popis";
	public static final String STATE = "Stav";

	public static final String PROCESSING = "Zpracování";
	public static final String REALIZABLE_QUESTION = "Je tato činnost realizovatelná?";
	public static final String IS_ONE_ACTION_QUESTION = "Skládá se pouze z jedné akce?";
	public static final String IS_USELESS_QUESTION = "Je zbytečná?";
	public static final String IS_SHORT_QUESTION = "Je proveditelná do dvou minut?";
	public static final String ARCHIVE_QUESTION = "Chcete činnost archivovat?";
	public static final String ERROR = "Nastala chyba v komunikaci s databází. Doporučujeme zkusit akci znvou nebo aplikaci vypnout a zapnout";
	public static final String YES = "Ano";
	public static final String NO = "Ne";
	public static final String CLOSE = "Zavřít";
	public static final String ARCHIVED = "Činnost byla archivována";
	public static final String POSTPONED = "Činnost byla odložena";
	public static final String DELETED = "Činnost byla smazána";
	public static final String CREATE_PROJECT = "Vytvořit projekt";
	public static final String CREATE_PROJECT_CHOOSE_PARENT = "Pokud má být nový projekt podprojektem již existujícího, zvolte ho prosím: ";
	public static final String CREATE_PROJECT_CHOOSE_GROUP = "Zvolte osoby, které chcete přidat do skupiny projektu";
	public static final String CREATE_TASK = "Vytvořit úkol";
	public static final String CREATE_TASK_CHOOSE_PROJECT = "Zvolte projekt, pod který chcete úkol přiřadit: ";
	public static final String CREATE_TASK_CHOOSE_OWNER = "Zvolte osobu, které chcete úkol přiřadit: ";

	// Tasks and projects tab
	// Details View
	public static final String PROJECT = "Projekt";
	public static final String TASK = "Úkol";
	public static final String ID = "ID";
	public static final String PARENT = "Projekt";
	public static final String PARENT_DESC = "Popis projektu";
	public static final String OWNER = "Vlastník";
	public static final String CREATOR = "Tvůrce";
	public static final String CONTEXT = "Kontext";
	public static final String GROUP = "Skupina";
	public static final String FROM = "Od";
	public static final String TO = "Do";
	public static final String CHANGE_OWNER = "Změnit vlastníka";
	public static final String SET_OWNER = "Zvolte nového vlastníka: ";
	public static final String NO_USER_SELECTED = "Zvolte nového vlastníka: ";

	// My Tasks Tab
	public static final String ACTIVE_TASKS = "Aktivní úkoly";
	public static final String ACTIVATE_TASK = "Aktivovat úkol";
	public static final String PLAN_TASK = "Naplánovat úkol";
	public static final String SET_INTERVAL = "Zadejte začátek a konec úkolu: ";
	public static final String TO_BEFORE_FROM = "Datum do je před datem od!";
	public static final String FINISH_TASK = "Dokončit úkol";
	public static final String FINISH_PROJECT = "Dokončit projekt";
	public static final String DELETE_PROJECT = "Smazat projekt";
	public static final String DELETE_TASK = "Smazat úkol";

}
