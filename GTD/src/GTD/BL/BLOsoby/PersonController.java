package GTD.BL.BLOsoby;
import GTD.BL.BLInterfaces.IPersonController;
import GTD.DL.DLEntity.Person;
import java.util.List;

/**
 * Třída implementuje interface IPersonController.
 * @author GTD team
 * @version 1.0
 */
public class PersonController implements IPersonController {

	private PersonAdmin personAdmin;

	/**
	 *
	 */
	public PersonController(){
		personAdmin = new PersonAdmin();
	}

	/**
	 * Zkusí přihlásit uživatele s danými přihlašovacími údaji.
	 * 
	 * @param login
	 * @param heslo
	 */
	@Override
	public boolean loginPerson(String login, String heslo, String hostname, String port, String sid, String schema){
		return personAdmin.loginOsoba(login, heslo, hostname, port, sid, schema);
	}

	/**
	 * Vytvoří nového uživatele.
	 * 
	 * @param osoba
	 */
	@Override
	public boolean addPerson(Person osoba){
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 */
	@Override
	public boolean deactivatePerson(Person osoba){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

	/**
	 * Vrátí přihlášeného uživatele.
	 */
	@Override
	public Person getLoggedPerson(){
		//return personAdmin.getPerson(GTDGUI.getMyself().getId());
		//return personAdmin.getPerson(18);
                return personAdmin.getOsobaPrihlasena();
	}

	/**
	 * Vrátí uživatele podle id.
	 * 
	 * @param id
	 */
	@Override
	public Person getPerson(int id){
		return personAdmin.getOsoba(id);
	}

	/**
	 * Odhlásí aktuálního uživatele.
	 */
	@Override
	public void logout(){

	}

	@Override
	public List getAllUsers() {
		return personAdmin.getAllUsers();
	}

}