package GTD.PL.PLView;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Třída představující pohled (okno) s formulářem pro zpracování činnosti.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:25
 */
public class ViewZpracovaniCinnosti extends JPanel implements IView {

	private JFrame processFrame;
	private JPanel visiblePanel;
	private JButton closeButton;
	private Cinnost cinnost;
	private List <Projekt> projects;
	private List <Osoba> users;
	private JList projectsList;
	private JList usersList;

	public ViewZpracovaniCinnosti(Cinnost cinnost){
		this.cinnost = cinnost;
		setPreferredSize (new Dimension (400, 85));
		closeButton = new JButton(Consts.CLOSE);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				processFrame.dispose();
			}
		});
		showFirstView();
	}

	void showFirstView() {
		visiblePanel = new JPanel(new FlowLayout());
		JButton yesButton = new JButton(Consts.YES);
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showRealizable();
			}
		});
		JButton noButton = new JButton(Consts.NO);
		noButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showNotRealizable();
			}
		});
		visiblePanel.add(new JLabel(Consts.REALIZABLE_QUESTION));
		visiblePanel.add(yesButton);
		visiblePanel.add(noButton);
		this.add(visiblePanel);
	}

	void showRealizable() {
		this.remove(visiblePanel);
		visiblePanel = new JPanel(new FlowLayout());
		JButton yesButton = new JButton(Consts.YES);
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showOneAction();
			}
		});
		JButton noButton = new JButton(Consts.NO);
		noButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreateProject();
			}
		});
		visiblePanel.add(new JLabel(Consts.IS_ONE_ACTION_QUESTION));
		visiblePanel.add(yesButton);
		visiblePanel.add(noButton);
		this.add(visiblePanel);
		refresh();
	}

	void showNotRealizable() {
		this.remove(visiblePanel);
		visiblePanel = new JPanel(new FlowLayout());
		JButton yesButton = new JButton(Consts.YES);
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showUseless();
			}
		});
		JButton noButton = new JButton(Consts.NO);
		noButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showNotUseless();
			}
		});
		visiblePanel.add(new JLabel(Consts.IS_USELESS_QUESTION));
		visiblePanel.add(yesButton);
		visiblePanel.add(noButton);
		this.add(visiblePanel);
		refresh();
	}
	
	void showOneAction() {
		this.remove(visiblePanel);
		visiblePanel = new JPanel(new FlowLayout());
		JButton yesButton = new JButton(Consts.YES);
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreateFinishedTask();
			}
		});
		JButton noButton = new JButton(Consts.NO);
		noButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCreateTask();
			}
		});
		visiblePanel.add(new JLabel(Consts.IS_SHORT_QUESTION));
		visiblePanel.add(yesButton);
		visiblePanel.add(noButton);
		this.add(visiblePanel);
		refresh();
	}

	void showNotUseless() {
		this.remove(visiblePanel);
		visiblePanel = new JPanel(new FlowLayout());
		JButton yesButton = new JButton(Consts.YES);
		yesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showArchive();
			}
		});
		JButton noButton = new JButton(Consts.NO);
		noButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showPostpone();
			}
		});
		visiblePanel.add(new JLabel(Consts.ARCHIVE_QUESTION));
		visiblePanel.add(yesButton);
		visiblePanel.add(noButton);
		this.add(visiblePanel);
		refresh();
	}

	void showCreateProject() {
		this.remove(visiblePanel);
		visiblePanel = new JPanel(new GridBagLayout());

		users = GTDGUI.getGTDGUI().getOsobaController().getAllUsers();
		usersList = new JList(users.toArray());
		usersList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		usersList.setVisibleRowCount(-1);
		JScrollPane usersScrollPane = new JScrollPane(usersList);
		usersScrollPane.setPreferredSize(new Dimension(200, 200));

		projects = GTDGUI.getGTDGUI().getProjektController().getAllProjekty();
		projectsList = new JList(projects.toArray());
		projectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projectsList.setVisibleRowCount(-1);
		projectsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedIndex = projectsList.getSelectedIndex();
				if (selectedIndex != -1) {
					users = projects.get(selectedIndex).getSkupina();
					usersList.setListData(users.toArray());
				}
			}
		});
		JScrollPane projectsScrollPane = new JScrollPane(projectsList);

		JButton createProjectButton = new JButton(Consts.CREATE_PROJECT);
		createProjectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List <Osoba> selectedUsers = new ArrayList<>();
				int selectedIndex = projectsList.getSelectedIndex();
				int rodicID = -1;
				if (selectedIndex != -1) {
					rodicID = projects.get(selectedIndex).getId();
				}
				int [] selectedIndices = usersList.getSelectedIndices();
				for (int i : selectedIndices) {
					selectedUsers.add(users.get(i));
				}
				if (GTDGUI.getGTDGUI().getProjektController().addProjekt(cinnost.getNazev(), cinnost.getPopis(), GTDGUI.getMyself().getId(), rodicID, selectedUsers, cinnost)) {
					GTDGUI.getGTDGUI().refresh();
					processFrame.dispose();
				}
			}
		});

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,20,10,20);
		c.gridx = 0;
		c.gridy = 0;
		visiblePanel.add(new JLabel(Consts.CREATE_PROJECT_CHOOSE_PARENT), c);
		c.gridy = 1;
		c.weighty = 2;
		visiblePanel.add(projectsScrollPane, c);
		c.gridx++;
		c.gridy = 0;
		c.weighty = 0;
		visiblePanel.add(new JLabel(Consts.CREATE_PROJECT_CHOOSE_GROUP), c);
		c.gridy = 1;
		c.weighty = 2;
		visiblePanel.add(usersScrollPane, c);
		c.gridx++;
		c.gridy = 1;
		c.weighty = 0;
		visiblePanel.add(createProjectButton, c);
		this.add(visiblePanel);
		setPreferredSize (new Dimension (1100, 300));
		processFrame.pack();
		processFrame.setLocationRelativeTo(null);
		refresh();
	}

	void showCreateTask() {
		this.remove(visiblePanel);
		visiblePanel = new JPanel(new GridBagLayout());

		users = GTDGUI.getGTDGUI().getOsobaController().getAllUsers();
		usersList = new JList(users.toArray());
		usersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usersList.setVisibleRowCount(-1);
		JScrollPane usersScrollPane = new JScrollPane(usersList);
		usersScrollPane.setPreferredSize(new Dimension(200, 200));

		projects = GTDGUI.getGTDGUI().getProjektController().getAllProjekty();
		projectsList = new JList(projects.toArray());
		projectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projectsList.setVisibleRowCount(-1);
		JScrollPane projectsScrollPane = new JScrollPane(projectsList);

		JButton createTaskButton = new JButton(Consts.CREATE_TASK);
		createTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int projektId = -1;
				int ownerId = -1;
				List <Osoba> selectedUsers = new ArrayList<>();

				int selectedIndex = projectsList.getSelectedIndex();
				if (selectedIndex != -1) {
					projektId = projects.get(selectedIndex).getId();
				}

				selectedIndex = usersList.getSelectedIndex();
				if (selectedIndex != -1) {
					ownerId = users.get(selectedIndex).getId();
				}
				if (GTDGUI.getGTDGUI().getUkolController().addUkol(cinnost.getNazev(), cinnost.getPopis(), ownerId, projektId, cinnost)) {
					GTDGUI.getGTDGUI().refresh();
					processFrame.dispose();
				}
			}
		});

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,20,10,20);
		c.gridx = 0;
		c.gridy = 0;
		visiblePanel.add(new JLabel(Consts.CREATE_TASK_CHOOSE_PROJECT), c);
		c.gridy = 1;
		c.weighty = 2;
		visiblePanel.add(projectsScrollPane, c);
		c.gridx++;
		c.gridy = 0;
		c.weighty = 0;
		visiblePanel.add(new JLabel(Consts.CREATE_TASK_CHOOSE_OWNER), c);
		c.gridy = 1;
		c.weighty = 2;
		visiblePanel.add(usersScrollPane, c);
		c.gridx++;
		c.gridy = 1;
		c.weighty = 0;
		visiblePanel.add(createTaskButton, c);
		this.add(visiblePanel);
		setPreferredSize (new Dimension (1000, 300));
		processFrame.pack();
		processFrame.setLocationRelativeTo(null);
		refresh();
	}

	void showCreateFinishedTask() {
		this.remove(visiblePanel);
		visiblePanel = new JPanel(new GridBagLayout());

		projects = GTDGUI.getGTDGUI().getProjektController().getAllProjekty();
		projectsList = new JList(projects.toArray());
		projectsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projectsList.setVisibleRowCount(-1);
		JScrollPane projectsScrollPane = new JScrollPane(projectsList);
		projectsScrollPane.setPreferredSize(new Dimension(200, 200));

		JButton createTaskButton = new JButton(Consts.CREATE_TASK);
		createTaskButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int projektId = -1;
				int selectedIndex = projectsList.getSelectedIndex();
				if (selectedIndex != -1) {
					projektId = projects.get(selectedIndex).getId();
				}

				if (GTDGUI.getGTDGUI().getUkolController().addTwoMinutesUkol(cinnost.getNazev(), cinnost.getPopis(), projektId, cinnost)) {
					GTDGUI.getGTDGUI().refresh();
					processFrame.dispose();
				}
			}
		});

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10,20,10,20);
		c.gridx = 0;
		c.gridy = 0;
		visiblePanel.add(new JLabel(Consts.CREATE_TASK_CHOOSE_PROJECT), c);
		c.gridy = 1;
		c.weighty = 2;
		visiblePanel.add(projectsScrollPane, c);
		c.gridx++;
		c.gridy = 1;
		c.weighty = 0;
		visiblePanel.add(createTaskButton, c);
		this.add(visiblePanel);
		setPreferredSize (new Dimension (600, 300));
		processFrame.pack();
		processFrame.setLocationRelativeTo(null);
		refresh();
	}

	void showUseless() {
		this.remove(visiblePanel);
		visiblePanel = new JPanel(new FlowLayout());
		if (GTDGUI.getGTDGUI().getCinnostController().deleteCinnost(cinnost)) {
			visiblePanel.add(new JLabel(Consts.DELETED));
			GTDGUI.getGTDGUI().refresh();
		} else {
			visiblePanel.add(new JLabel(Consts.ERROR));
		}
		visiblePanel.add(closeButton);
		this.add(visiblePanel);
		refresh();
	}

	void showArchive() {
		this.remove(visiblePanel);
		visiblePanel = new JPanel(new FlowLayout());
		if (GTDGUI.getGTDGUI().getCinnostController().archiveCinnost(cinnost)) {
			visiblePanel.add(new JLabel(Consts.ARCHIVED));
			GTDGUI.getGTDGUI().refresh();
		} else {
			visiblePanel.add(new JLabel(Consts.ERROR));
		}
		visiblePanel.add(closeButton);
		this.add(visiblePanel);
		refresh();
	}

	void showPostpone() {
		this.remove(visiblePanel);
		visiblePanel = new JPanel(new FlowLayout());
		if (GTDGUI.getGTDGUI().getCinnostController().postponeCinnost(cinnost)) {
			visiblePanel.add(new JLabel(Consts.POSTPONED));
			GTDGUI.getGTDGUI().refresh();
		} else {
			visiblePanel.add(new JLabel(Consts.ERROR));
		}
		visiblePanel.add(closeButton);
		this.add(visiblePanel);
		refresh();
	}

	/**
	 * Aktualizuje pohled.
	 */
	public void refresh(){
		processFrame.revalidate();
		processFrame.repaint();
	}

	/**
	 * Zobrazí daný pohled.
	 */
	public void showView(){
		processFrame = new JFrame(Consts.PROCESSING);
		processFrame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		processFrame.getContentPane().add (this);
		processFrame.pack();
		processFrame.setLocationRelativeTo(null);
		processFrame.setVisible (true);
	}

}