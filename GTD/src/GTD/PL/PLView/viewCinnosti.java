package GTD.PL.PLView;
import GTD.DL.DLEntity.Cinnost;
import GTD.PL.PLController.GTDEventHandler;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	private JTextField newActivityTitleField;
	private JTextField newActivityDescField;
	private JButton newActivityButton;
	private JButton processActivityButton;
	private JButton deleteActivityButton;

	private CinnostiTableModel cinnostiTableModel;
	private JTable cinnostiTable;

	public viewCinnosti(mainFrame mainFrame){
		this.mainFrame = mainFrame;
		init();
	}

	void init() {
		setLayout(new BorderLayout());
		initMenu();
		initMainView();
		add(menu, BorderLayout.PAGE_START);
		add(mainView, BorderLayout.CENTER);
	}

	void initMenu() {
		menu = new JPanel(new FlowLayout());
		newActivityTitleField = new JTextField(12);
		newActivityDescField = new JTextField(25);
		newActivityButton = new JButton(Consts.ADD_ACTIVITY);
		newActivityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (newActivityTitleField.getText() == "") {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.TITLE_EMPTY);
				} else if (newActivityDescField.getText() == "") {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.DESC_EMPTY);
				} else {
					Cinnost newActivity = new Cinnost(newActivityTitleField.getText(), newActivityDescField.getText(), 48, GTDGUI.getMyself().getId());
					if (GTDGUI.getGTDGUI().getCinnostController().addCinnost(newActivity)) {
						refresh();
					}
				}
			}
		}) ;
		processActivityButton = new JButton(Consts.PROCESS_ACTIVITY);
		menu.add(new JLabel(Consts.TITLE + ": "));
		menu.add(newActivityTitleField);
		menu.add(new JLabel(Consts.DESC + ": "));
		menu.add(newActivityDescField);
		menu.add(newActivityButton);
		menu.add(processActivityButton);
	}

	void initMainView() {
		loadData();
		mainView = new JPanel(new BorderLayout());
		cinnostiTableModel = new CinnostiTableModel();
		cinnostiTable = new JTable(cinnostiTableModel);
		JScrollPane scrollPane = new JScrollPane(cinnostiTable);
		mainView.add(scrollPane);
	}

	void loadData() {
		cinnosti = GTDGUI.getGTDGUI().getCinnostController().getCinnostiOsoby(GTDGUI.getGTDGUI().getMyself());
	}

	class CinnostiTableModel extends AbstractTableModel {

		@Override
		public int getRowCount() {
			return cinnosti.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int column) {
			return (new String[] {Consts.TITLE, Consts.DESC, Consts.STATE})[column];
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
		loadData();
		cinnostiTableModel.fireTableDataChanged();
	}

	/**
	 * Zobrazí daný pohled.
	 */
	public void showView(){
		mainFrame.addTab(Consts.ACTIVITES, this);
	}

}