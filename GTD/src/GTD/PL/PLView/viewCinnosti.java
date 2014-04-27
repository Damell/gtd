package GTD.PL.PLView;
import GTD.PL.PLController.GTDEventHandler;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 * Třída představující pohled (okno) s nezpracovanými uživatelovými činnostmi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class viewCinnosti implements IView {

	private GTDEventHandler eventHandler;
	/**
	 * Kolekce nezpracovaných činností uživatele.
	 */
	private JList cinnosti;
	/**
	 * Textové pole pro název nové činnosti
	 */
	private JTextField newCinnostJmeno;
	/**
	 * Textové pole pro popis nové činnosti
	 */
	private JTextField newCinnostPopis;
	/**
	 * Tlačítko pro uložení nové činnosti
	 */
	private JButton addCinnostButton;
	/**
	 * Tlačítko pro zpracování označené činnosti
	 */
	private JButton zpracujCinnostButton;
	/**
	 * Tlačítko pro smazání označené činnosti
	 */
	private JButton deleteCinnostButton;

	public viewCinnosti(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Aktualizuje pohled.
	 */
	public void refresh(){

	}

	/**
	 * Zobrazí daný pohled.
	 */
	public void show(){

	}

}