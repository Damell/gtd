package GTD.PL.PLView;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Projekt;
import GTD.PL.PLController.GTDEventHandler;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * Třída představující pohled (okno) s formulářem pro zpracování činnosti.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:25
 */
public class ViewZpracovaniCinnosti extends JPanel implements IView {

	private GTDEventHandler eventHandler;

	private JFrame processFrame;
	private JPanel visiblePanel;
	private JButton closeButton;
	private Cinnost cinnost;
	private List<Projekt> projects;
	private JList projectsList;

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
		visiblePanel = new JPanel(new BorderLayout());
		List<String> projectNames = new ArrayList<>();
		projects = GTDGUI.getGTDGUI().getProjektController().getProjektyOsoby(GTDGUI.getMyself());
		for (Projekt project : projects) {
			projectNames.add(project.getNazev());
		}
		projectsList = new JList(projectNames.toArray());
		JButton createProjectButton = new JButton(Consts.CREATE_PROJECT);
		createProjectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (GTDGUI.getGTDGUI().getProjektController().addProjekt(cinnost.getNazev(), cinnost.getPopis(), GTDGUI.getMyself().getId(), projectsList.getSelectedIndex(), cinnost)) {
					GTDGUI.getGTDGUI().refresh();
					processFrame.dispose();
				}
			}
		});
		visiblePanel.add(new JLabel(Consts.CREATE_PROJECT_CHOOSE_PARENT), BorderLayout.PAGE_START);
		visiblePanel.add(projectsList, BorderLayout.CENTER);
		visiblePanel.add(createProjectButton, BorderLayout.PAGE_END);
		this.add(visiblePanel);
		setPreferredSize (new Dimension (600, 200));
		processFrame.pack();
		refresh();
	}

	void showCreateTask() {

	}

	void showCreateFinishedTask() {

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
		processFrame.setLocationRelativeTo(null);
		processFrame.pack();
		processFrame.setVisible (true);
	}

}