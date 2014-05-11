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
	UtilDateModel fromModel;
	UtilDateModel toModel;
	JFrame frame;
	Ukol selectedUkol;
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
					selectedUkol = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
					getDatesFromUser();
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
					selectedUkol = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
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

	void planTask (Interval interval) {
		if(GTDGUI.getGTDGUI().getUkolController().scheduleUkol(selectedUkol, interval)) {
			GTDGUI.getGTDGUI().refresh();
		}
	}

	void getDatesFromUser () {
		Interval interval = null;

		// init date pickers
		fromModel = new UtilDateModel();
		JDatePanelImpl fromDatePanel = new JDatePanelImpl(fromModel);
		JDatePickerImpl fromDatePicker = new JDatePickerImpl(fromDatePanel);
		toModel = new UtilDateModel();
		JDatePanelImpl toDatePanel = new JDatePanelImpl(toModel);
		JDatePickerImpl toDatePicker = new JDatePickerImpl(toDatePanel);
		// set default today's date
		fromModel.setValue(new Date());
		toModel.setValue(new Date());

		JButton planButton = new JButton(Consts.PLAN_TASK);
		planButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fromModel.getValue().before(toModel.getValue())) {
					frame.dispose();
					planTask(new Interval(fromModel.getValue(), toModel.getValue()));
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.TO_BEFORE_FROM);
				}
			}
		});

		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel(Consts.SET_INTERVAL));
		panel.add(fromDatePicker);
		panel.add(toDatePicker);
		panel.add(planButton);

		frame = new JFrame(Consts.ACTIVATE_TASK);
		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible (true);
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