package GTD.BL.BLOsoby;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.BL.BLInterfaces.IOsobaController;
import GTD.DL.DLEntity.Osoba;
import GTD.PL.PLView.GTDGUI;
import java.util.List;

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
		GUI = GTDGUI.getGTDGUI();
		spravceOsob = new SpravceOsob();
	}

	/**
	 * Zkusí přihlásit uživatele s danými přihlašovacími údaji.
	 * 
	 * @param login
	 * @param heslo
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
		//return spravceOsob.getOsoba(GTDGUI.getMyself().getId());
		//return spravceOsob.getOsoba(18);
                return spravceOsob.getOsobaPrihlasena();
	}

	/**
	 * Vrátí uživatele podle id.
	 * 
	 * @param id
	 */
	@Override
	public Osoba getOsoba(int id){
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