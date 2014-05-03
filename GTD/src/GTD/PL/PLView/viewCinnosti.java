package GTD.PL.PLView;
import GTD.DL.DLEntity.Cinnost;
import GTD.PL.PLController.GTDEventHandler;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

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
	private JPanel mainView;
	private List<Cinnost> cinnosti;
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
	private JTable cinnostiTable;
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
		initMainView();
		add(menu);
	}

	void initMenu() {
		menu = new JPanel(new FlowLayout());
		newActivityButton = new JButton(Consts.ADD_ACTIVITY);
		processActivityButton = new JButton(Consts.PROCESS_ACTIVITY);
		menu.add(newActivityButton);
		menu.add(processActivityButton);
	}

	void initMainView() {
		mainView = new JPanel(new BorderLayout());
		loadCinnosti();
		cinnostiTable = new JTable(new CinnostiTableModel());
		JScrollPane scrollPane = new JScrollPane(cinnostiTable);
		mainView.add(scrollPane);
		add(mainView);
	}

	void loadCinnosti() {
		cinnosti = GTDGUI.getGTDGUI().getCinnostController().getCinnostiOsoby(GTDGUI.getGTDGUI().getMyself());
	}

	class CinnostiTableModel extends AbstractTableModel {

		@Override
		public int getRowCount() {
			return cinnosti.size();
		}

		@Override
		public int getColumnCount() {
			return 4;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String value = "";
			switch(columnIndex) {
				case 0: {
					return cinnosti.get(rowIndex).getNazev();
				}
				case 1: {
					return cinnosti.get(rowIndex).getPopis();
				}
				case 2: {
					return cinnosti.get(rowIndex).getStav();
				}
				case 3: {
					return cinnosti.get(rowIndex).getStavPopis();
				}
			}
			return value;
		}

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