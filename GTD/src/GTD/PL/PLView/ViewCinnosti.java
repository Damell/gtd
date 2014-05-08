package GTD.PL.PLView;
import GTD.DL.DLEntity.Cinnost;
import GTD.PL.PLController.GTDEventHandler;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class ViewCinnosti extends JPanel implements IView {

	private GTDEventHandler eventHandler;
	private MainFrame mainFrame;
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

	public ViewCinnosti(MainFrame mainFrame){
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
		newActivityTitleField = new JTextField(10);
		newActivityDescField = new JTextField(20);
		newActivityButton = new JButton(Consts.ADD_ACTIVITY);
		newActivityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (newActivityTitleField.getText().equals("")) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.TITLE_EMPTY);
				} else if (newActivityDescField.getText().equals("")) {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.DESC_EMPTY);
				} else {
					Cinnost newActivity = new Cinnost(newActivityTitleField.getText(), newActivityDescField.getText(), 48, GTDGUI.getMyself().getId());
					System.out.println(newActivityTitleField.getText());
					if (GTDGUI.getGTDGUI().getCinnostController().addCinnost(newActivity)) {
						refresh();
					}
				}
			}
		}) ;
		processActivityButton = new JButton(Consts.PROCESS_ACTIVITY);
		processActivityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
 				int selectedRow = cinnostiTable.getSelectedRow();
				if(selectedRow != -1) {
					Cinnost selected = cinnosti.get(cinnostiTable.convertRowIndexToModel(selectedRow));
					GTDGUI.getGTDGUI().showZpracovaniCinnosti(selected);
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.NO_ACTIVITY_SELECTED);
				}
			}
		});
		deleteActivityButton = new JButton(Consts.DELETE_ACTIVITY);
		deleteActivityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
 				int selectedRow = cinnostiTable.getSelectedRow();
				if(selectedRow != -1) {
					Cinnost selected = cinnosti.get(cinnostiTable.convertRowIndexToModel(selectedRow));
					if ( GTDGUI.getGTDGUI().getCinnostController().deleteCinnost(selected) ) {
						refresh();
					}
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.NO_ACTIVITY_SELECTED);
				}
			}
		});

		menu.add(processActivityButton);
		menu.add(deleteActivityButton);
		menu.add(new JLabel(Consts.TITLE + ": "));
		menu.add(newActivityTitleField);
		menu.add(new JLabel(Consts.DESC + ": "));
		menu.add(newActivityDescField);
		menu.add(newActivityButton);
	}

	void initMainView() {
		loadData();
		mainView = new JPanel(new BorderLayout());
		cinnostiTableModel = new CinnostiTableModel();
		cinnostiTable = new JTable(cinnostiTableModel);
		cinnostiTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					int selectedRow = target.getSelectedRow();
					Cinnost selected = cinnosti.get(cinnostiTable.convertRowIndexToModel(selectedRow));
					GTDGUI.getGTDGUI().showZpracovaniCinnosti(selected);
				}
			}
		});
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