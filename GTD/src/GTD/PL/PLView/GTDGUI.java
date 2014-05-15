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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Hlavní třída uživatelského rozhraní - obsahuje základní navigaci a kolekci
 * pohledů (views).
 * @version 1.0
 */
public class GTDGUI implements IGTDGUI {

	/**
	 * Kolekce pohledů (obrazovek).
	 */
	private List<IView> views;
	private MainFrame mainFrame;

	// BL reference 
	private IPersonController osobaController;
	private IActivityController cinnostController;
	private IContextController kontextController;
	private IProjectController projektController;
	private ITaskController ukolController;

	private Person myself;

	private static IView loginPanel;
	private static IView cinnostiPanel;
	private static IView ukolyProjektyPanel;
	private static IView mojeUkolyPanel;
	private static IView zpracovaniPanel;
	private static GTDGUI GTDGUI;

	/**
	 *
	 */
	public GTDGUI(){
		views = new ArrayList<>() ;
		initBL();

		//Init main frame
		mainFrame = new MainFrame(Consts.APP_TITLE);
	}

	void initBL() {
		osobaController = new PersonController();
		cinnostController = new ActivityController();
		kontextController = new ContextController();
		projektController = new ProjectController();
		ukolController = new TaskController();
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		GTDGUI = new GTDGUI();
		GTDGUI.showPrihlaseni();
	}


	/**
	 * Aktualizuje všechny navázané pohledy.
	 */
	@Override
	public void refresh(){
		mainFrame.revalidate();
		mainFrame.repaint();
		if (ukolyProjektyPanel!= null) ukolyProjektyPanel.refresh();
		if (cinnostiPanel != null) cinnostiPanel.refresh();
		if (mojeUkolyPanel != null) mojeUkolyPanel.refresh();
	}

	/**
	 * Zobrazí dialog se zpracováním činnosti.
	 * 
	 * @param cinnost
	 */
	@Override
	public void showZpracovaniCinnosti(Activity cinnost){
		zpracovaniPanel = new ViewProcessActivity(cinnost);
		zpracovaniPanel.showView();
	}

	/**
	 *
	 */
	public void showMainWindow() {
		myself = getOsobaController().getPrihlasenaOsoba();
		showCinnosti();
		showUkolyProjekty();
		showMojeUkoly();
	}

	/**
	 * Zobrazí okno s úkoly a projekty všech osob
	 */
	@Override
	public void showUkolyProjekty(){
		ukolyProjektyPanel = new ViewTasksProjects(mainFrame);
		ukolyProjektyPanel.showView();
	}

	/**
	 * Zobrazí činnosti přihlášené osoby.
	 */
	@Override
	public void showCinnosti(){
		cinnostiPanel = new ViewActivities(mainFrame);
		cinnostiPanel.showView();
	}

	/**
	 * Zobrazí úkoly přihlášené osoby.
	 */
	@Override
	public void showMojeUkoly(){
		mojeUkolyPanel = new ViewMyTasks(mainFrame);
		mojeUkolyPanel.showView();
	}

	/**
	 * Zobrazí přihlašovací okno.
	 */
	@Override
	public void showPrihlaseni(){
		//Init login panel
		loginPanel = new ViewLogin(mainFrame);
		views.add(loginPanel);
		loginPanel.showView();
	}

	@Override
	public void showError(String error) {
		JOptionPane optionPane = new JOptionPane();
		optionPane.showMessageDialog(mainFrame, error);
	}

	/**
	 *
	 * @return
	 */
	public static GTDGUI getGTDGUI() {
		return GTDGUI;
	}

	/**
	 *
	 * @return
	 */
	public static Person getMyself() {
		return GTDGUI.myself;
	}

	/**
	 *
	 * @return
	 */
	public IPersonController getOsobaController() {
		return osobaController;
	}

	/**
	 *
	 * @return
	 */
	public IActivityController getCinnostController() {
		return cinnostController;
	}

	/**
	 *
	 * @return
	 */
	public IContextController getKontextController() {
		return kontextController;
	}

	/**
	 *
	 * @return
	 */
	public IProjectController getProjektController() {
		return projektController;
	}

	/**
	 *
	 * @return
	 */
	public ITaskController getUkolController() {
		return ukolController;
	}

}