package GTD.PL.PLView;
import GTD.DL.DLEntity.Action;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Task;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
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
 * View with all projects and tasks and detail view of any selected
 * @author GTD team
 * @version 1.0
 */
public class ViewTasksProjects extends JPanel implements IView {
	
	private MainFrame mainFrame;
	private JPanel menu;
	private JPanel mainView;
	private JTree projectsTree;
	private ProjectTreeModel projectTreeModel;
	private List<Project> projects;
	private List<Task> tasks;
	private DetailView detailView;
	
	/**
	 *
	 * @param mainFrame
	 */
	public ViewTasksProjects(MainFrame mainFrame){
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
				showDetailOfSelected();
			}
		});
		JScrollPane projectsScrollPane = new JScrollPane(projectsTree);

		detailView = new DetailView();
		mainView.add(projectsScrollPane);
		mainView.add(detailView);
	}

	void loadData() {
		projects = GTDGUI.getGTDGUI().getProjectController().getAllProjects();
		tasks = GTDGUI.getGTDGUI().getTaskController().getAllUkoly();
	}

	void showDetailOfSelected() {
		Action node = (Action) projectsTree.getLastSelectedPathComponent();
		detailView.refresh();
		if (node == null) {
			detailView.showNone();
		} else if (node instanceof Task) {
			detailView.showTask((Task) node);
		} else {
			detailView.showProject((Project) node);
		}
	}

	class DetailView extends JPanel {
		GridBagConstraints c;
		DetailView () {
			setLayout(new GridBagLayout());
		}
		void showTask (Task task) {
			removeAll();
			c = new GridBagConstraints();
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(10,20,10,20);
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 2;
			JLabel title = new JLabel(Consts.TASK + " #" + task.getId());
			title.setFont(title.getFont().deriveFont(20f));
			add(title, c);
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy++;
			c.weightx = 1;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			add(new JLabel(Consts.TITLE + ": "), c);
			c.gridx = 1;
			JLabel label = new JLabel();
			label.setText("<html>" + task.getNazev() + "</html>");
			label.setMaximumSize(new Dimension(1, 1));
			add(label, c);
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
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.OWNER + ": "), c);
			c.gridx = 1;
			Person vlastnik = GTDGUI.getGTDGUI().getPersonController().getPerson(task.getVlastnik_id());
			add(new JLabel(vlastnik.getJmeno() + " " + vlastnik.getPrijmeni()), c);
			if(task.getKalendar() != null && task.getKalendar().isSet()) {
				c.gridx = 0;
				c.gridy++;
				add(new JLabel(Consts.FROM + ": "), c);
				c.gridx = 1;
				add(new JLabel(task.getKalendar().getFrom().toString()), c);
				c.gridx = 0;
				c.gridy++;
				add(new JLabel(Consts.TO + ": "), c);
				c.gridx = 1;
				add(new JLabel(task.getKalendar().getTo().toString()), c);
			}
			if(task.getKontext() != null && task.getKontext().getKontextNazev() != null) {
				c.gridx = 0;
				c.gridy++;
				add(new JLabel(Consts.CONTEXT + ": "), c);
				c.gridx = 1;
				add(new JLabel(task.getKontext().getKontextNazev()), c);
			}
			JButton changeOwnerButton = new JButton(Consts.CHANGE_OWNER);
			changeOwnerButton.addActionListener(new ActionListener() {
				Task task;
				ActionListener setTask(Task task) {
					this.task = task;
					return this;
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					new ViewChangeOwner(task);
				}
			}.setTask(task));
			JButton activateTaskButton = new JButton(Consts.ACTIVATE_TASK);
			activateTaskButton.addActionListener(new ActionListener() {
				Task task;
				ActionListener setTask(Task task) {
					this.task = task;
					return this;
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					if(GTDGUI.getGTDGUI().getTaskController().activateTask(task)) {
						GTDGUI.getGTDGUI().refresh();
					}
				}
			}.setTask(task));
			JButton planTaskButton = new JButton(Consts.PLAN_TASK);
			planTaskButton.addActionListener(new ActionListener() {
				Task task;
				ActionListener setTask(Task task) {
					this.task = task;
					return this;
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					new ViewPlanTask(task);
				}
			}.setTask(task));
			JButton finishTaskButton = new JButton(Consts.FINISH_TASK);
			finishTaskButton.addActionListener(new ActionListener() {
				Task task;
				ActionListener setTask(Task task) {
					this.task = task;
					return this;
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					if(GTDGUI.getGTDGUI().getTaskController().finishTask(task)) {
						GTDGUI.getGTDGUI().refresh();
					}
				}
			}.setTask(task));
			JButton deleteTaskButton = new JButton(Consts.DELETE_TASK);
			deleteTaskButton.addActionListener(new ActionListener() {
				Task task;
				ActionListener setTask(Task task) {
					this.task = task;
					return this;
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					if(GTDGUI.getGTDGUI().getTaskController().deleteTask(task)) {
						GTDGUI.getGTDGUI().refresh();
					}
				}
			}.setTask(task));
			c.gridwidth = 2;
			c.gridx = 0;
			c.gridy++;
			add(changeOwnerButton, c);
			c.gridy++;
			add(activateTaskButton, c);
			c.gridy++;
			add(planTaskButton, c);
			c.gridy++;
			add(finishTaskButton, c);
			c.gridy++;
			add(deleteTaskButton, c);

			refresh();
		}

		void showProject (Project project) {
			removeAll();
			c = new GridBagConstraints();
			c.anchor = GridBagConstraints.FIRST_LINE_START;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.insets = new Insets(10,20,10,20);
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 2;
			JLabel title = new JLabel(Consts.PROJECT + " #" + project.getId());
			title.setFont(title.getFont().deriveFont(20f));
			add(title, c);
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy++;
			c.weightx = 1;
			c.anchor = GridBagConstraints.FIRST_LINE_START;
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
			Person vlastnik = GTDGUI.getGTDGUI().getPersonController().getPerson(project.getVlastnik_id());
			add(new JLabel(vlastnik.getJmeno() + " " + vlastnik.getPrijmeni()), c);
			c.gridx = 0;
			c.gridy++;
			add(new JLabel(Consts.GROUP + ": "), c);
			c.gridx = 1;
			JList group = new JList(project.getSkupina().toArray());
			group.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			group.setVisibleRowCount(-1);
			add(group, c);

			JButton changeOwnerButton = new JButton(Consts.CHANGE_OWNER);
			changeOwnerButton.addActionListener(new ActionListener() {
				Project project;
				ActionListener setProject(Project project) {
					this.project = project;
					return this;
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					new ViewChangeOwner(project);
				}
			}.setProject(project));
			JButton finishProjectButton = new JButton(Consts.FINISH_PROJECT);
			finishProjectButton.addActionListener(new ActionListener() {
				Project project;
				ActionListener setProject(Project project) {
					this.project = project;
					return this;
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					if(GTDGUI.getGTDGUI().getProjectController().finishProject(project)) {
						GTDGUI.getGTDGUI().refresh();
					}
				}
			}.setProject(project));
			JButton deleteProjectButton = new JButton(Consts.DELETE_PROJECT);
			deleteProjectButton.addActionListener(new ActionListener() {
				Project project;
				ActionListener setProject(Project project) {
					this.project = project;
					return this;
				}
				@Override
				public void actionPerformed(ActionEvent e) {
					if(GTDGUI.getGTDGUI().getProjectController().deleteProject(project)) {
						GTDGUI.getGTDGUI().refresh();
					}
				}
			}.setProject(project));
			c.gridwidth = 2;
			c.gridx = 0;
			c.gridy++;
			add(changeOwnerButton, c);
			c.gridy++;
			add(finishProjectButton, c);
			c.gridy++;
			add(deleteProjectButton, c);

			refresh();
		}
		void showNone() {
			removeAll();
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
			Project root = new Project(-1, Consts.PROJECTS, "", 0, "", GTDGUI.getMyself().getId());
			for (Task el : tasks) {
				if (el.getProjekt() == 0) {
					root.addUkol(el);
				}
			}
			return root;
		}

		@Override
		public Object getChild(Object parent, int index) {
			Project par = (Project) parent;
			List <Action> childs = new ArrayList<>();
			if (par.getNazev().equals(Consts.PROJECTS)) {
				for (Project el : projects) {
					if (el.getRodic() == null) {
						childs.add(el);
					}
				}
			} else {
				childs.addAll(par.getProjekty());
			}
			for (Task ukol : par.getUkoly()) {
				childs.add(ukol);
			}
			return childs.get(index);
		}

		@Override
		public int getChildCount(Object parent) {
			Project par = (Project) parent;
			int count = 0;
			if (par.getNazev().equals(Consts.PROJECTS)) {
				for (Project el : projects) {
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
			return node instanceof Task;
		}

		@Override
		public void valueForPathChanged(TreePath path, Object newValue) {
		}

		@Override
		public int getIndexOfChild(Object parent, Object child) {
			Project par = (Project) parent;
			List <Action> childs = new ArrayList<>();
			if (par.getNazev().equals(Consts.PROJECTS)) {
				for (Project el : projects) {
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
	
	public void refresh(){
		TreePath path = projectsTree.getSelectionPath();
		loadData();
		projectTreeModel = new ProjectTreeModel();
		projectsTree.setModel(projectTreeModel);
		projectsTree.setSelectionPath(path);
		detailView.showNone();
		
	}
	
	public void showView(){
		mainFrame.addTab(Consts.TASKS_AND_PROJECTS, this);
	}
	
}