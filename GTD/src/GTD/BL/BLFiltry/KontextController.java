package GTD.BL.BLFiltry;
import GTD.BL.BLInterfaces.*;
import GTD.DL.DLEntity.*;

/**
 * Třída implementuje interface IKontextController.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:23
 */
public class KontextController implements IKontextController {

	private SpravceKontextu spravceKontextu;
	private IGTDGUI GUI;

	public KontextController(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Přidá nový kotext zadané osobě.
	 * 
	 * @param kontext
	 * @param osoba
	 */
	public boolean addKontext(Kontext kontext, Osoba osoba){
		return false;
	}

	/**
	 * Smaže kontext.
	 * 
	 * @param kontext
	 */
	public boolean deleteKontext(Kontext kontext){
		return false;
	}

	/**
	 * Změní název kontextu.
	 * 
	 * @param kontext
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