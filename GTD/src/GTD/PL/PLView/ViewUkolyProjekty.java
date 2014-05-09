package GTD.PL.PLView;
import GTD.DL.DLEntity.Aktivita;
import GTD.DL.DLEntity.Projekt;
import GTD.DL.DLEntity.Ukol;
import GTD.PL.PLController.GTDEventHandler;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import sun.awt.X11.XConstants;

/**
 * Třída představující pohled (okno) s úkoly a projekty.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class ViewUkolyProjekty extends JPanel implements IView {
	
	private GTDEventHandler eventHandler;
	private MainFrame mainFrame;
	
	private JPanel menu;
	
	private JPanel mainView;
	private JTree projectsTree;
	private ProjectTreeModel projectTreeModel;
	private List<Projekt> projects;
	private JPanel detailView;
	
	public ViewUkolyProjekty(MainFrame mainFrame){
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

		projectTreeModel = new ProjectTreeModel();
		projectsTree = new JTree(projectTreeModel);
		JScrollPane projectsScrollPane = new JScrollPane(projectsTree);
		mainView.add(projectsScrollPane);

	}

	void loadData() {
		projects = GTDGUI.getGTDGUI().getProjektController().getAllProjekty();
	}

	class ProjectTreeModel implements TreeModel {
		private Vector<TreeModelListener> treeModelListeners = new Vector<TreeModelListener>();
		@Override
		public Object getRoot() {
			return new Projekt(-1, Consts.PROJECTS, "", 0, "", GTDGUI.getMyself().getId());
		}

		@Override
		public Object getChild(Object parent, int index) {
			Projekt par = (Projekt) parent;
			List <Aktivita> childs = new ArrayList<>();
			if (par.getNazev().equals(Consts.PROJECTS)) {
				for (Projekt el : projects) {
					if (el.getRodic() == null) {
						childs.add(el);
					}
				}
			} else {
				childs.addAll(par.getProjekty());
			}
			for (Ukol ukol : par.getUkoly()) {
				childs.add(ukol);
			}
			return childs.get(index);
		}

		@Override
		public int getChildCount(Object parent) {
			Projekt par = (Projekt) parent;
			int count = 0;
			if (par.getNazev().equals(Consts.PROJECTS)) {
				for (Projekt el : projects) {
					if (el.getRodic() == null) {
						count++;
					}
				}
			} else {
				count += par.getProjekty().size();
			}
			count += par.getUkoly().size();
			return count;
		}

		@Override
		public boolean isLeaf(Object node) {
			return node instanceof Ukol;
		}

		@Override
		public void valueForPathChanged(TreePath path, Object newValue) {
		}

		@Override
		public int getIndexOfChild(Object parent, Object child) {
			Projekt par = (Projekt) parent;
			List <Aktivita> childs = new ArrayList<>();
			if (par.getNazev().equals(Consts.PROJECTS)) {
				for (Projekt el : projects) {
					if (el.getRodic() == null) {
						childs.add(el);
					}
				}
			} else {
				childs.addAll(par.getProjekty());
			}
			childs.addAll(par.getUkoly());
			return childs.indexOf(child);
		}

		@Override
		public void addTreeModelListener(TreeModelListener l) {
			treeModelListeners.addElement(l);
		}

		@Override
		public void removeTreeModelListener(TreeModelListener l) {
			treeModelListeners.removeElement(l);
		}
	}
	
	/**
	 * Aktualizuje pohled.
	 */
	public void refresh(){
		loadData();
	}
	
	/**
	 * Zobrazí daný pohled.
	 */
	public void showView(){
		mainFrame.addTab(Consts.TASKS_AND_PROJECTS, this);
	}
	
}