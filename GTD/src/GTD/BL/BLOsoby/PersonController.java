package GTD.BL.BLOsoby;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.BL.BLInterfaces.IPersonController;
import GTD.DL.DLEntity.Person;
import GTD.PL.PLView.GTDGUI;
import java.util.List;

/**
 * Třída implementuje interface IPersonController.
 * @version 1.0
 */
public class PersonController implements IPersonController {

	private PersonAdmin spravceOsob;
	private IGTDGUI GUI;

	/**
	 *
	 */
	public PersonController(){
		GUI = GTDGUI.getGTDGUI();
		spravceOsob = new PersonAdmin();
	}

	/**
	 * Zkusí přihlásit uživatele s danými přihlašovacími údaji.
	 * 
	 * @param login
	 * @param heslo
	 * @return 
	 */
	@Override
	public boolean loginPerson(String login, String heslo){
		return spravceOsob.loginOsoba(login, heslo);
	}

	/**
	 * Vytvoří nového uživatele.
	 * 
	 * @param osoba
	 * @return 
	 * @return  
	 */
	@Override
	public boolean addPerson(Person osoba){
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 * @return 
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
	 * @return 
	 */
	@Override
	public Person getLoggedPerson(){
		//return spravceOsob.getPerson(GTDGUI.getMyself().getId());
		//return spravceOsob.getPerson(18);
                return spravceOsob.getOsobaPrihlasena();
	}

	/**
	 * Vrátí uživatele podle id.
	 * 
	 * @param id
	 * @return 
	 */
	@Override
	public Person getPerson(int id){
		return spravceOsob.getOsoba(id);
	}

	/**
	 * Odhlásí aktuálního uživatele.
	 */
	@Override
	public void logout(){

	}

	@Override
	public List getAllUsers() {
		return spravceOsob.getAllUsers();
	}

}