package GTD.PL.PLView;
import GTD.BL.BLAktivity.ActivityController;
import GTD.BL.BLAktivity.ProjectController;
import GTD.BL.BLAktivity.TaskController;
import GTD.BL.BLFiltry.ContextController;
import GTD.BL.BLInterfaces.IActivityController;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.BL.BLInterfaces.IContextController;
import GTD.BL.BLInterfaces.IPersonController;
import GTD.BL.BLInterfaces.IProjectController;
import GTD.BL.BLInterfaces.ITaskController;
import GTD.BL.BLOsoby.PersonController;
import GTD.DL.DLEntity.Activity;
import GTD.DL.DLEntity.Person;
import javax.swing.JOptionPane;

/**
 * Main Class of the GUI - contains references to all main building blocks (mostly panels) of the app and controls interaction between them
 * @author GTD team
 * @version 1.0
 */
public class GTDGUI implements IGTDGUI {

	private MainFrame mainFrame;
	private Person myself;
	private static GTDGUI GTDGUI;

	private static IView loginPanel;
	private static IView activitiesPanel;
	private static IView tasksProjectsPanel;
	private static IView myTasksPanel;
	private static IView processActivityPanel;

	private IPersonController personController;
	private IActivityController activityController;
	private IContextController contextController;
	private IProjectController projectController;
	private ITaskController taskController;

	/**
	 * Private constructor -- makes this class a singleton
	 */
	private GTDGUI(){
		initBL();

		//Init main frame
		mainFrame = new MainFrame(Consts.APP_TITLE);
	}

	/**
	 * Initializes BL
	 */
	void initBL() {
		personController = new PersonController();
		activityController = new ActivityController();
		contextController = new ContextController();
		projectController = new ProjectController();
		taskController = new TaskController();
	}

	/**
	 * main method - runs first
	 * @param args
	 */
	public static void main(String[] args) {
		GTDGUI = new GTDGUI();
		GTDGUI.showPrihlaseni();
	}

	@Override
	public void refresh(){
		mainFrame.revalidate();
		mainFrame.repaint();
		if (tasksProjectsPanel!= null) tasksProjectsPanel.refresh();
		if (activitiesPanel != null) activitiesPanel.refresh();
		if (myTasksPanel != null) myTasksPanel.refresh();
	}

	@Override
	public void showProcessActivity(Activity cinnost){
		processActivityPanel = new ViewProcessActivity(cinnost);
		processActivityPanel.showView();
	}

	/**
	 * Shows main UI when the users logs in
	 */
	public void showMainWindow() {
		myself = getPersonController().getPrihlasenaOsoba();
		showActivities();
		showUkolyProjekty();
		showMojeUkoly();
	}

	@Override
	public void showUkolyProjekty(){
		tasksProjectsPanel = new ViewTasksProjects(mainFrame);
		tasksProjectsPanel.showView();
	}

	@Override
	public void showActivities(){
		activitiesPanel = new ViewActivities(mainFrame);
		activitiesPanel.showView();
	}

	@Override
	public void showMojeUkoly(){
		myTasksPanel = new ViewMyTasks(mainFrame);
		myTasksPanel.showView();
	}

	@Override
	public void showPrihlaseni(){
		//Init login panel
		loginPanel = new ViewLogin(mainFrame);
		loginPanel.showView();
	}

	@Override
	public void showError(String error) {
		JOptionPane optionPane = new JOptionPane();
		optionPane.showMessageDialog(mainFrame, error);
	}

	/**
	 * @return instance of this class
	 */
	public static GTDGUI getGTDGUI() {
		return GTDGUI;
	}

	/**
	 * @return instance of signed in user
	 */
	public static Person getMyself() {
		return GTDGUI.myself;
	}

	/**
	 * @return instance of BL class
	 */
	public IPersonController getPersonController() {
		return personController;
	}

	/**
	 * @return instance of BL class
	 */
	public IActivityController getActivityController() {
		return activityController;
	}

	/**
	 * @return instance of BL class
	 */
	public IContextController getContextController() {
		return contextController;
	}

	/**
	 * @return instance of BL class
	 */
	public IProjectController getProjectController() {
		return projectController;
	}

	/**
	 * @return instance of BL class
	 */
	public ITaskController getTaskController() {
		return taskController;
	}

}