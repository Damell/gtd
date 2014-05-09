package GTD.PL.PLView;
import GTD.DL.DLEntity.Aktivita;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import GTD.DL.DLEntity.Ukol;
import GTD.PL.PLController.GTDEventHandler;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

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
	private DetailView detailView;
	
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
		projectsTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				Aktivita node = (Aktivita) projectsTree.getLastSelectedPathComponent();
				if (node == null) return;
				if (node instanceof Ukol) {
					detailView.showTask((Ukol) node);
				} else {
					detailView.showProject((Projekt) node);
				}
			}
		});
		JScrollPane projectsScrollPane = new JScrollPane(projectsTree);

		detailView = new DetailView();
		mainView.add(projectsScrollPane);
		mainView.add(detailView);
	}

	void loadData() {
		projects = GTDGUI.getGTDGUI().getProjektController().getAllProjekty();
	}

	class DetailView extends JPanel {
		GridBagConstraints c;
		DetailView () {
			setLayout(new GridBagLayout());
		}
		void showTask (Ukol task) {
			removeAll();
			c = new GridBagConstraints();
			c.anchor = GridBagConstraints.PAGE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(10,20,10,20);
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 2;
			JLabel title = new JLabel(Consts.TASK);
			title.setFont(title.getFont().deriveFont(20f));
			add(title, c);
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.TITLE + ": "), c);
			c.gridx = 1;
			add(new JLabel(task.getNazev()), c);
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.DESC + ": "), c);
			c.gridx = 1;
			add(new JLabel(task.getPopis()), c);
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.STATE + ": "), c);
			c.gridx = 1;
			add(new JLabel(task.getStavPopis()), c);
			/*
			c.gridx = 0;
			c.gridy = 4;
			add(new JLabel(Consts.PARENT + ": "), c);
			c.gridx = 1;
			c.gridy = 4;
			add(new JLabel(task.getProjektNazev()), c);
			c.gridx = 0;
			c.gridy = 5;
			add(new JLabel(Consts.PARENT_DESC + ": "), c);
			c.gridx = 1;
			c.gridy = 5;
			add(new JLabel(task.getProjektPopis()), c);
			*/
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.OWNER + ": "), c);
			c.gridx = 1;
			Osoba vlastnik = GTDGUI.getGTDGUI().getOsobaController().getOsoba(task.getVlastnik_id());
			add(new JLabel(vlastnik.getJmeno() + " " + vlastnik.getPrijmeni()), c);
			if(task.getKontext() != null && task.getKontext().getKontextNazev() != null) {
				c.gridx = 0;
				c.gridy++;
				add(new JLabel(Consts.CONTEXT + ": "), c);
				c.gridx = 1;
				add(new JLabel(task.getKontext().getKontextNazev()), c);
			}

			refresh();
		}

		void showProject (Projekt project) {
			removeAll();
			c = new GridBagConstraints();
			c.anchor = GridBagConstraints.PAGE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(10,20,10,20);
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 2;
			JLabel title = new JLabel(Consts.PROJECT);
			title.setFont(title.getFont().deriveFont(20f));
			add(title, c);
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.TITLE + ": "), c);
			c.gridx = 1;
			add(new JLabel(project.getNazev()), c);
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.DESC + ": "), c);
			c.gridx = 1;
			add(new JLabel(project.getPopis()), c);
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.STATE + ": "), c);
			c.gridx = 1;
			add(new JLabel(project.getStavPopis()), c);
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.OWNER + ": "), c);
			c.gridx = 1;
			Osoba vlastnik = GTDGUI.getGTDGUI().getOsobaController().getOsoba(project.getVlastnik_id());
			add(new JLabel(vlastnik.getJmeno() + " " + vlastnik.getPrijmeni()), c);
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.GROUP + ": "), c);
			c.gridx = 1;
			JList group = new JList(project.getSkupina().toArray());
			group.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			group.setVisibleRowCount(-1);
			add(group, c);

			refresh();
		}
		void refresh() {
			revalidate();
			repaint();
		}
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
		projectTreeModel = new ProjectTreeModel();
		projectsTree.setModel(projectTreeModel);
		
	}
	
	/**
	 * Zobrazí daný pohled.
	 */
	public void showView(){
		mainFrame.addTab(Consts.TASKS_AND_PROJECTS, this);
	}
	
}