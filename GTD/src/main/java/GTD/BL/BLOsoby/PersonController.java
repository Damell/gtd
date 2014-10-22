package GTD.BL.BLOsoby;

import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.DL.DLEntity.Person;
import GTD.BL.BLInterfaces.IPersonController;
import java.util.List;

/**
 * Třída implementuje interface IPersonController.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:54
 */
public class PersonController implements IPersonController {

	private IGTDGUI GUI;
	private PersonAdmin spravceOsob;



	public void finalize() throws Throwable {

	}

	public PersonController(){

	}

	/**
	 * Vytvoří nového uživatele.
	 * @return
	 * @return
	 * 
	 * @param osoba
	 */
	@Override
	public boolean addPerson(Person osoba){
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * @return
	 * 
	 * @param osoba
	 */
	@Override
	public boolean deactivatePerson(Person osoba){
		return false;
	}

	@Override
	public List getAllUsers(){
		return null;
	}

	/**
	 * Vrátí přihlášeného uživatele.
	 * @return
	 */
	@Override
	public Person getLoggedPerson(){
		return null;
	}

	/**
	 * Vrátí uživatele podle id.
	 * @return
	 * 
	 * @param id
	 */
	@Override
	public Person getPerson(int id){
		return null;
	}

	/**
	 * Zkusí přihlásit uživatele s danými přihlašovacími údaji.
	 * @return
	 * 
	 * @param login
	 * @param heslo
	 */
	@Override
	public boolean loginPerson(String login, String heslo){
		return false;
	}

	/**
	 * Odhlásí aktuálního uživatele.
	 */
	@Override
	public void logout(){

	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

}