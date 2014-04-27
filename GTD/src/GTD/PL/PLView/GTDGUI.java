package GTD.PL.PLView;
import GTD.PL.PLController.GTDEventHandler;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import java.util.List;
import javax.swing.JButton;

/**
 * Hlavní třída uživatelského rozhraní - obsahuje základní navigaci a kolekci
 * pohledů (views).
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public class GTDGUI implements IGTDGUI {

	/**
	 * Kolekce pohledů (obrazovek).
	 */
	private List<IView> views;
	private GTDEventHandler eventHandler;
	private JButton cinnostiButton;
	private JButton UkolyProjektyButton;

	public GTDGUI(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Aktualizuje všechny navázané pohledy.
	 */
	public void refresh(){

	}

	/**
	 * Zobrazí činnosti zadané osoby.
	 * 
	 * @param osoba
	 */
	public void showCinnosti(Osoba osoba){

	}

	/**
	 * Zobrazí dialog se zpracováním činnosti.
	 * 
	 * @param cinnost
	 */
	public void showZpracovaniCinnosti(Cinnost cinnost){

	}

	/**
	 * Zobrazí okno s úkoly a podprojekty zadaného projektu.
	 * 
	 * @param projekt
	 */
	public void showUkolyProjekty(Projekt projekt){

	}

	/**
	 * Zobrazí přihlašovací okno.
	 */
	public void showPrihlaseni(){

	}

}