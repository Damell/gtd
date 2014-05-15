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
	public boolean loginOsoba(String login, String heslo){
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
	public boolean addOsoba(Person osoba){
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 * @return 
	 */
	@Override
	public boolean deactivateOsoba(Person osoba){
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
	public Person getPrihlasenaOsoba(){
		//return spravceOsob.getOsoba(GTDGUI.getMyself().getId());
		//return spravceOsob.getOsoba(18);
                return spravceOsob.getOsobaPrihlasena();
	}

	/**
	 * Vrátí uživatele podle id.
	 * 
	 * @param id
	 * @return 
	 */
	@Override
	public Person getOsoba(int id){
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