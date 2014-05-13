package GTD.PL.PLView;
import GTD.DL.DLDAO.DAOStav;
import GTD.DL.DLEntity.Cinnost;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

/**
 * Třída představující pohled (okno) s nezpracovanými uživatelovými činnostmi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class ViewCinnosti extends JPanel implements IView {

	private MainFrame mainFrame;
	private JPanel menu;
	private JPanel mainView;
	private List<Cinnost> activities;

	private JTextField newActivityTitleField;
	private JTextField newActivityDescField;
	private JButton newActivityButton;
	private JButton processActivityButton;
	private JButton deleteActivityButton;

	private ActivityTableModel activityTableModel;
	private JTable activityTable;
	private TableRowSorter<ActivityTableModel> activitySorter;

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
		
		JCheckBox toProcessFilterCheckBox = new JCheckBox(Consts.ACTIVITIES_TO_PROCESS);
		toProcessFilterCheckBox.addItemListener(new ItemListener() {
			RowFilter<ActivityTableModel,Integer> activeFilter = new RowFilter<ActivityTableModel, Integer>() {
				int toProcessID = new DAOStav().getCinnostKeZpracovaniID();
				@Override
				public boolean include(RowFilter.Entry<? extends ActivityTableModel, ? extends Integer> entry) {
					int id = entry.getModel().getStav(entry.getIdentifier());
					return id == toProcessID;
				}
			};

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					activitySorter.setRowFilter(activeFilter);
					activityTable.setRowSorter(activitySorter);
				} else {
					activitySorter.setRowFilter(null);
				}
			}
		});

		newActivityTitleField = new JTextField(8);
		newActivityDescField = new JTextField(14);
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
					if (GTDGUI.getGTDGUI().getCinnostController().addCinnost(newActivityTitleField.getText(), newActivityDescField.getText())) {
						newActivityTitleField.setText("");
						newActivityDescField.setText("");
						refresh();
					}
				}
			}
		}) ;
		processActivityButton = new JButton(Consts.PROCESS_ACTIVITY);
		processActivityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
 				int selectedRow = activityTable.getSelectedRow();
				if(selectedRow != -1) {
					Cinnost selected = activities.get(activityTable.convertRowIndexToModel(selectedRow));
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
 				int selectedRow = activityTable.getSelectedRow();
				if(selectedRow != -1) {
					Cinnost selected = activities.get(activityTable.convertRowIndexToModel(selectedRow));
					if ( GTDGUI.getGTDGUI().getCinnostController().deleteCinnost(selected) ) {
						refresh();
					}
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.NO_ACTIVITY_SELECTED);
				}
			}
		});

		menu.add(toProcessFilterCheckBox);
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
		activityTableModel = new ActivityTableModel();
		activitySorter = new TableRowSorter<>(activityTableModel);
		activityTable = new JTable(activityTableModel);
		activityTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					int selectedRow = target.getSelectedRow();
					Cinnost selected = activities.get(activityTable.convertRowIndexToModel(selectedRow));
					GTDGUI.getGTDGUI().showZpracovaniCinnosti(selected);
				}
			}
		});
		JScrollPane scrollPane = new JScrollPane(activityTable);
		mainView.add(scrollPane);
	}

	void loadData() {
		activities = GTDGUI.getGTDGUI().getCinnostController().getCinnostiOsoby(GTDGUI.getGTDGUI().getMyself());
	}

	class ActivityTableModel extends AbstractTableModel {

		public int getStav(int index) {
			return activities.get(index).getStav();
		}

		@Override
		public int getRowCount() {
			return activities.size();
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
					return activities.get(rowIndex).getNazev();
				}
				case 1: {
					return activities.get(rowIndex).getPopis();
				}
				case 2: {
					return activities.get(rowIndex).getStavPopis();
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
		activityTableModel.fireTableDataChanged();
	}

	/**
	 * Zobrazí daný pohled.
	 */
	public void showView(){
		mainFrame.addTab(Consts.ACTIVITES, this);
	}

}