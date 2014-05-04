package GTD.PL.PLView;
import GTD.DL.DLEntity.Projekt;
import GTD.DL.DLEntity.Ukol;
import GTD.PL.PLController.GTDEventHandler;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 * Třída představující pohled (okno) s úkoly a projekty.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class viewUkolyProjekty extends JPanel implements IView {
	
	private GTDEventHandler eventHandler;
	private mainFrame mainFrame;
	
	private JPanel menu;
	
	private JPanel mainView;
	private JTable projectsTable;
	private JTable tasksTable;
	private ProjectTableModel projectModel;
	private TaskTableModel taskModel;
	private List<Projekt> projects;
	private List<Ukol> tasks;
	
	public viewUkolyProjekty(mainFrame mainFrame){
		this.mainFrame = mainFrame;
		init();
	}
	
	void init() {
		setLayout(new BorderLayout());
		initMenu();
		initMainView();
		add(menu);
		add(mainView);
	}
	
	void initMenu() {
		menu = new JPanel(new FlowLayout());
	}
	
	void initMainView() {
		loadData();
		mainView = new JPanel(new GridLayout(1, 2));

		projectModel = new ProjectTableModel();
		projectsTable = new JTable(projectModel);
		projectsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projectsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel)e.getSource();
				tasks = projects.get(lsm.getLeadSelectionIndex()).getUkoly();
				//GTDGUI.getGTDGUI().refresh();
				taskModel.fireTableDataChanged();
			}
		});
		JScrollPane projectsScrollPane = new JScrollPane(projectsTable);

		taskModel = new TaskTableModel();
		tasksTable = new JTable(taskModel);
		tasksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane tasksScrollPane = new JScrollPane(tasksTable);

		mainView.add(projectsScrollPane);
		mainView.add(tasksScrollPane);
	}

	void loadData() {
		projects = GTDGUI.getGTDGUI().getProjektController().getProjektyOsoby(GTDGUI.getGTDGUI().getMyself());
		tasks = new ArrayList<Ukol>();
	}
	
	class ProjectTableModel extends AbstractTableModel {

		@Override
		public int getRowCount() {
			return projects.size();
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
					return projects.get(rowIndex).getNazev();
				}
				case 1: {
					return projects.get(rowIndex).getPopis();
				}
				case 2: {
					return projects.get(rowIndex).getStavPopis();
				}
			}
			return value;
		}
		
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

		void refresh() {
			fireTableDataChanged();
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
		mainFrame.addTab(Consts.TASKS_AND_PROJECTS, this);
	}
	
}