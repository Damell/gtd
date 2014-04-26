package GTD.PL.PLController;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.BL.BLInterfaces.ICinnostController;
import GTD.BL.BLInterfaces.IUkolController;
import GTD.BL.BLInterfaces.IProjektController;
import GTD.BL.BLInterfaces.IKontextController;
import GTD.BL.BLInterfaces.IOsobaController;

/**
 * Třída starající se o komunikaci z uživatelského rozhraní do business vrstvy.
 * Slouží k oddělení funkčnosti GUI a jeho vzhledu.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:21
 */
public class GTDEventHandler {

	private IGTDGUI GUI;
	private ICinnostController cinnostController;
	private IUkolController ukolController;
	private IProjektController projektController;
	private IKontextController kontextController;
	private IOsobaController osobaController;

	public GTDEventHandler(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Metoda odbavující všechny příchozí požadavky (požadavky z GUI) - podle
	 * konkrétního eventu se metoda rozhodne, co dál. Už tady se vytváří objekty, se
	 * kterými pracují nižší vrstvy (BL a DL).
	 * 
	 * @param event    Objekt se všemi informacemi potřebnými pro rozhodnutí, co má
	 * třída dál udělat.
	 */
	public void actionPerformed(Event event){

	}

	/**
	 * Vrátí činnosti konkrétní osoby.
	 * 
	 * @param osoba
	 */
	public List<Cinnost> getCinnostiOsoby(Osoba osoba){
		return null;
	}

	/**
	 * Zobrazí dialog pro zpracování dané činnosti.
	 * 
	 * @param cinnost
	 */
	public void showZpracovaniCinnosti(Cinnost cinnost){

	}

}