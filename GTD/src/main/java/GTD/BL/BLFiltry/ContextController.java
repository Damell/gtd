package GTD.BL.BLFiltry;

import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Context;
import GTD.BL.BLInterfaces.IContextController;

/**
 * Třída implementuje interface IContextController.
 * @author Šimon
 * @version 1.0
 * @created 19-10-2014 12:30:51
 */
public class ContextController implements IContextController {

	private IGTDGUI GUI;
	private ContextAdmin spravceKontextu;



	public void finalize() throws Throwable {

	}

	public ContextController(){

	}

	/**
	 * Přidá nový kotext zadané osobě.
	 * @return
	 * 
	 * @param kontext
	 * @param osoba
	 */
	@Override
	public boolean addContext(Context kontext, Person osoba){
		return false;
	}

	/**
	 * Smaže kontext.
	 * @return
	 * 
	 * @param kontext
	 */
	@Override
	public boolean deleteContext(Context kontext){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

	/**
	 * Změní název kontextu.
	 * @return
	 * 
	 * @param kontext
	 */
	@Override
	public boolean updateContext(Context kontext){
		return false;
	}

}