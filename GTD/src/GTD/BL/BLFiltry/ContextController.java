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

	/**
	 * Přidá nový kotext zadané osobě.
	 * 
	 * @param kontext
	 * @param osoba
	 * @return 
	 */
	@Override
	public boolean addContext(Context kontext, Person osoba){
		return false;
	}

	/**
	 * Smaže kontext.
	 * 
	 * @param kontext
	 * @return 
	 */
	@Override
	public boolean deleteContext(Context kontext){
		return false;
	}

	/**
	 * Změní název kontextu.
	 * 
	 * @param kontext
	 * @return 
	 */
	@Override
	public boolean updateContext(Context kontext){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

}