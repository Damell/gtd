package GTD.BL.BLFiltry;
import GTD.BL.BLInterfaces.*;
import GTD.DL.DLEntity.*;

/**
 * Třída implementuje interface IContextController.
 * @version 1.0
 */
public class ContextController implements IContextController {

	private ContextAdmin spravceKontextu;
	private IGTDGUI GUI;

	/**
	 *
	 */
	public ContextController(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Přidá nový kotext zadané osobě.
	 * 
	 * @param kontext
	 * @param osoba
	 * @return 
	 */
	public boolean addKontext(Context kontext, Person osoba){
		return false;
	}

	/**
	 * Smaže kontext.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean deleteKontext(Context kontext){
		return false;
	}

	/**
	 * Změní název kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean updateKontext(Context kontext){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh(){

	}

}