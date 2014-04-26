package GTD.BL.BLOsoby;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.BL.BLInterfaces.IOsobaController;
import GTD.DL.DLEntity.Osoba;

/**
 * Třída implementuje interface IOsobaController.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:23
 */
public class OsobaController implements IOsobaController {

	private SpravceOsob spravceOsob;
	private IGTDGUI GUI;

	public OsobaController(){

	}

	/**
	 * Zkusí přihlásit uživatele s danými přihlašovacími údaji.
	 * 
	 * @param login
	 * @param heslo
	 */
	@Override
	public boolean loginOsoba(String login, String heslo){
		return false;
	}

	/**
	 * Vytvoří nového uživatele.
	 * 
	 * @param osoba
	 * @return 
	 * @return  
	 */
	@Override
	public boolean addOsoba(Osoba osoba){
		return false;
	}

	/**
	 * Deaktivuje uživatele (na jeho účet se nepůjde přihlásit).
	 * 
	 * @param osoba
	 */
	@Override
	public boolean deactivateOsoba(Osoba osoba){
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
	public Osoba getPrihlasenaOsoba(){
		return null;
	}

	/**
	 * Odhlásí aktuálního uživatele.
	 */
	@Override
	public void logout(){

	}

}