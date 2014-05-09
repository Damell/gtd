package GTD.PL.PLView;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Ukol;
import GTD.PL.PLController.GTDEventHandler;
import GTD.PL.PLView.ViewCinnosti.CinnostiTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * Třída představující pohled (okno) s nezpracovanými uživatelovými činnostmi.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class ViewMojeUkoly extends JPanel implements IView {

	private GTDEventHandler eventHandler;
	private MainFrame mainFrame;
	private JPanel menu;
	private JPanel mainView;
	private List<Ukol> tasks;

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
		finishTaskButton = new JButton(Consts.FINISH_TASK);
		finishTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
 				int selectedRow = tasksTable.getSelectedRow();
				if(selectedRow != -1) {
					Ukol selected = tasks.get(tasksTable.convertRowIndexToModel(selectedRow));
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.NO_ACTIVITY_SELECTED);
				}
			}
		}) ;

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
					return tasks.get(rowIndex).getNazev();
				}
				case 1: {
					return tasks.get(rowIndex).getPopis();
				}
				case 2: {
					return tasks.get(rowIndex).getStavPopis();
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