package GTD.PL.PLView;
import GTD.DL.DLDAO.DAOState;
import GTD.DL.DLEntity.Task;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

/**
 * Třída představující pohled (okno) s nezpracovanými uživatelovými činnostmi.
 * @version 1.0
 */
public class ViewMyTasks extends JPanel implements IView {

	private MainFrame mainFrame;
	private JPanel menu;
	private JPanel mainView;
	private List<Task> tasks;

	private JButton changeOwnerButton;
	private JButton activateTaskButton;
	private JButton planTaskButton;
	private JButton finishTaskButton;
	private JButton deleteTaskButton;

	private TaskTableModel tasksTableModel;
	private TableRowSorter<TaskTableModel> tasksSorter;
	private JTable tasksTable;

	/**
	 *
	 * @param mainFrame
	 */
	public ViewMyTasks(MainFrame mainFrame){
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


		JCheckBox activeFilterCheckBox = new JCheckBox(Consts.ACTIVE_TASKS);
		activeFilterCheckBox.addItemListener(new ItemListener() {
			RowFilter<TaskTableModel,Integer> activeFilter = new RowFilter<TaskTableModel, Integer>() {
				int activeID = new DAOState().getUkolAktivniID();
				int inCalendarID = new DAOState().getUkolVKalendariID();
				@Override
				public boolean include(RowFilter.Entry<? extends TaskTableModel, ? extends Integer> entry) {
					int id = entry.getModel().getStav(entry.getIdentifier());
					return id == activeID || id == inCalendarID;
				}
			};

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					tasksSorter.setRowFilter(activeFilter);
					tasksTable.setRowSorter(tasksSorter);
				} else {
					tasksSorter.setRowFilter(null);
				}
			}
		});
		changeOwnerButton = new JButton(Consts.CHANGE_OWNER);
		changeOwnerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tasksTable.getSelectedRow();
				if(selectedRow != -1) {
					Task selected = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
					new ViewChangeOwner(selected);
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.NO_ACTIVITY_SELECTED);
				}
			}
		});
		activateTaskButton = new JButton(Consts.ACTIVATE_TASK);
		activateTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tasksTable.getSelectedRow();
				if(selectedRow != -1) {
					Task selected = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
					if(GTDGUI.getGTDGUI().getUkolController().activateUkol(selected)) {
						GTDGUI.getGTDGUI().refresh();
					}
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.NO_ACTIVITY_SELECTED);
				}
			}
		});
		planTaskButton = new JButton(Consts.PLAN_TASK);
		planTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tasksTable.getSelectedRow();
				if(selectedRow != -1) {
					Task selected = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
					new ViewPlanTask(selected);
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.NO_ACTIVITY_SELECTED);
				}
			}
		});
		finishTaskButton = new JButton(Consts.FINISH_TASK);
		finishTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tasksTable.getSelectedRow();
				if(selectedRow != -1) {
					Task selected = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
					if(GTDGUI.getGTDGUI().getUkolController().finishUkol(selected)) {
						GTDGUI.getGTDGUI().refresh();
					}
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.NO_ACTIVITY_SELECTED);
				}
			}
		}) ;
		deleteTaskButton = new JButton(Consts.DELETE_TASK);
		deleteTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tasksTable.getSelectedRow();
				if(selectedRow != -1) {
					Task selected = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
					if(GTDGUI.getGTDGUI().getUkolController().deleteUkol(selected)) {
						GTDGUI.getGTDGUI().refresh();
					}
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.NO_ACTIVITY_SELECTED);
				}
			}
		});
		
		menu.add(activeFilterCheckBox);
		menu.add(changeOwnerButton);
		menu.add(activateTaskButton);
		menu.add(planTaskButton);
		menu.add(finishTaskButton);
		menu.add(deleteTaskButton);
	}

	void initMainView() {
		loadData();
		mainView = new JPanel(new BorderLayout());
		tasksTableModel = new TaskTableModel();
		tasksSorter = new TableRowSorter<>(tasksTableModel);
		tasksTable = new JTable(tasksTableModel);
		JScrollPane scrollPane = new JScrollPane(tasksTable);
		mainView.add(scrollPane);
	}

	void loadData() {
		tasks = GTDGUI.getGTDGUI().getUkolController().getUkolyOsoby(GTDGUI.getMyself());
	}

	class TaskTableModel extends AbstractTableModel {

		public int getStav(int index) {
			return tasks.get(index).getStav();
		}

		@Override
		public int getRowCount() {
			return tasks.size();
		}

		@Override
		public int getColumnCount() {
			return 6;
		}

		@Override
		public String getColumnName(int column) {
			return (new String[] {Consts.ID, Consts.TITLE, Consts.DESC, Consts.STATE, Consts.PARENT, Consts.PARENT_DESC})[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String value = "";
			switch(columnIndex) {
				case 0: {
					return tasks.get(rowIndex).getId();
				}
				case 1: {
					return tasks.get(rowIndex).getNazev();
				}
				case 2: {
					return tasks.get(rowIndex).getPopis();
				}
				case 3: {
					return tasks.get(rowIndex).getStavPopis();
				}
				case 4: {
					return tasks.get(rowIndex).getProjektNazev();
				}
				case 5: {
					return tasks.get(rowIndex).getProjektPopis();
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
		tasksTableModel.fireTableDataChanged();
	}

	/**
	 * Zobrazí daný pohled.
	 */
	public void showView(){
		mainFrame.addTab(Consts.MY_TASKS, this);
	}

}