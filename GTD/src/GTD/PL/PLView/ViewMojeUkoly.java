package GTD.PL.PLView;
import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Ukol;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 * Třída představující pohled (okno) s nezpracovanými uživatelovými činnostmi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class ViewMojeUkoly extends JPanel implements IView {

	private MainFrame mainFrame;
	private JPanel menu;
	private JPanel mainView;
	private List<Ukol> tasks;

	private JButton activateTaskButton;
	private JButton planTaskButton;
	private JButton finishTaskButton;

	private TaskTableModel tasksTableModel;
	private JTable tasksTable;

	public ViewMojeUkoly(MainFrame mainFrame){
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
		activateTaskButton = new JButton(Consts.ACTIVATE_TASK);
		activateTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
 				int selectedRow = tasksTable.getSelectedRow();
				if(selectedRow != -1) {
					Ukol selected = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
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
					Ukol selected = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
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
					Ukol selected = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
					if(GTDGUI.getGTDGUI().getUkolController().finishUkol(selected)) {
						GTDGUI.getGTDGUI().refresh();
					}
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.NO_ACTIVITY_SELECTED);
				}
			}
		}) ;

		menu.add(activateTaskButton);
		menu.add(planTaskButton);
		menu.add(finishTaskButton);
	}

	void initMainView() {
		loadData();
		mainView = new JPanel(new BorderLayout());
		tasksTableModel = new TaskTableModel();
		tasksTable = new JTable(tasksTableModel);
		JScrollPane scrollPane = new JScrollPane(tasksTable);
		mainView.add(scrollPane);
	}

	void loadData() {
		tasks = GTDGUI.getGTDGUI().getUkolController().getUkolyOsoby(GTDGUI.getMyself());
	}

	class TaskTableModel extends AbstractTableModel {

		@Override
		public int getRowCount() {
			return tasks.size();
		}

		@Override
		public int getColumnCount() {
			return 5;
		}

		@Override
		public String getColumnName(int column) {
			return (new String[] {Consts.TITLE, Consts.DESC, Consts.STATE, Consts.PARENT, Consts.PARENT_DESC})[column];
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			String value = "";
			switch(columnIndex) {
				case 0: {
					return tasks.get(rowIndex).getNazev();
				}
				case 1: {
					return tasks.get(rowIndex).getPopis();
				}
				case 2: {
					return tasks.get(rowIndex).getStavPopis();
				}
				case 3: {
					return tasks.get(rowIndex).getProjektNazev();
				}
				case 4: {
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