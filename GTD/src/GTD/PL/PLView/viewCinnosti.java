package GTD.PL.PLView;
import GTD.PL.PLController.GTDEventHandler;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Třída představující pohled (okno) s nezpracovanými uživatelovými činnostmi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class viewCinnosti extends JPanel implements IView {

	private GTDEventHandler eventHandler;
	private mainFrame mainFrame;
	private JPanel menu;
	/**
	 * Tlačítko pro vložení nové činnosti
	 */
	private JButton newActivityButton;
	/**
	 * Tlačítko pro zpracování nové činnosti
	 */
	private JButton processActivityButton;

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
	 * Tlačítko pro zpracování označené činnosti
	 */
	private JButton zpracujCinnostButton;
	/**
	 * Tlačítko pro smazání označené činnosti
	 */
	private JButton deleteCinnostButton;

	public viewCinnosti(mainFrame mainFrame){
		this.mainFrame = mainFrame;
		init();
	}

	void init() {
		setLayout(new GridLayout(2, 1));
		initMenu();
		add(menu);
	}

	void initMenu() {
		menu = new JPanel(new FlowLayout());
		newActivityButton = new JButton(Consts.ADD_ACTIVITY);
		processActivityButton = new JButton(Consts.PROCESS_ACTIVITY);
		menu.add(newActivityButton);
		menu.add(processActivityButton);
	}

	/**
	 * Aktualizuje pohled.
	 */
	public void refresh(){

	}

	/**
	 * Zobrazí daný pohled.
	 */
	public void showView(){
		mainFrame.addTab(Consts.ACTIVITES, this);
	}

}