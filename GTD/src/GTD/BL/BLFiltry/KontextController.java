package GTD.BL.BLFiltry;
import GTD.BL.BLInterfaces.*;
import GTD.DL.DLEntity.*;

/**
 * Třída implementuje interface IKontextController.
 * @version 1.0
 */
public class KontextController implements IKontextController {

	private SpravceKontextu spravceKontextu;
	private IGTDGUI GUI;

	/**
	 *
	 */
	public KontextController(){

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
	public boolean addKontext(Kontext kontext, Osoba osoba){
		return false;
	}

	/**
	 * Smaže kontext.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean deleteKontext(Kontext kontext){
		return false;
	}

	/**
	 * Změní název kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	public boolean updateKontext(Kontext kontext){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	public void refresh(){

	}

}