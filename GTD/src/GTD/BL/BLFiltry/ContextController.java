package GTD.BL.BLFiltry;
import GTD.BL.BLInterfaces.*;
import GTD.DL.DLEntity.*;

/**
 * Třída implementuje interface IContextController.
 * @author GTD team
 * @version 1.0
 */
public class ContextController implements IContextController {

	private ContextAdmin spravceKontextu;

	/**
	 *
	 */
	public ContextController(){

	}

	/**
	 * Přidá nový kotext zadané osobě.
	 * 
	 * @param context
	 * @param person
	 */
	@Override
	public boolean addContext(Context context, Person person){
		return false;
	}

	/**
	 * Smaže context.
	 * 
	 * @param context
	 */
	@Override
	public boolean deleteContext(Context context){
		return false;
	}

	/**
	 * Změní název kontextu.
	 * 
	 * @param context
	 */
	@Override
	public boolean updateContext(Context context){
		return false;
	}

	/**
	 * Odešle GUI pokyn k obnovení.
	 */
	@Override
	public void refresh(){

	}

}