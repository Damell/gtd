package GTD.PL.PLView;
import GTD.BL.BLAktivity.CinnostController;
import GTD.BL.BLAktivity.ProjektController;
import GTD.BL.BLAktivity.UkolController;
import GTD.BL.BLFiltry.KontextController;
import GTD.BL.BLInterfaces.ICinnostController;
import GTD.PL.PLController.GTDEventHandler;
import GTD.BL.BLInterfaces.IGTDGUI;
import GTD.BL.BLInterfaces.IKontextController;
import GTD.BL.BLInterfaces.IOsobaController;
import GTD.BL.BLInterfaces.IProjektController;
import GTD.BL.BLInterfaces.IUkolController;
import GTD.BL.BLOsoby.OsobaController;
import GTD.DL.DLEntity.Cinnost;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import java.util.ArrayList;
import java.util.List;

/**
 * Hlavní třída uživatelského rozhraní - obsahuje základní navigaci a kolekci
 * pohledů (views).
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:22
 */
public class GTDGUI implements IGTDGUI {

	/**
	 * Kolekce pohledů (obrazovek).
	 */
	private List<IView> views;
	private GTDEventHandler eventHandler;
	private MainFrame mainFrame;

	// BL reference 
	private IOsobaController osobaController;
	private ICinnostController cinnostController;
	private IKontextController kontextController;
	private IProjektController projektController;
	private IUkolController ukolController;

	private Osoba myself;

	private static IView loginPanel;
	private static IView cinnostiPanel;
	private static IView ukolyProjektyPanel;
	private static IView mojeUkolyPanel;
	private static IView zpracovaniPanel;
	private static GTDGUI GTDGUI;

	public GTDGUI(){
		views = new ArrayList<>() ;
		initBL();

		//Init main frame
		mainFrame = new MainFrame(Consts.APP_TITLE);
	}

	void initBL() {
		osobaController = new OsobaController();
		cinnostController = new CinnostController();
		kontextController = new KontextController();
		projektController = new ProjektController();
		ukolController = new UkolController();
	}

	public static void main(String[] args) {
		GTDGUI = new GTDGUI();
		GTDGUI.showPrihlaseni();
	}


	/**
	 * Aktualizuje všechny navázané pohledy.
	 */
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
	public void showZpracovaniCinnosti(Cinnost cinnost){
		zpracovaniPanel = new ViewZpracovaniCinnosti(cinnost);
		zpracovaniPanel.showView();
	}

	public void showMainWindow() {
		myself = getOsobaController().getPrihlasenaOsoba();
		showCinnosti();
		showUkolyProjekty();
		showMojeUkoly();
	}

	/**
	 * Zobrazí okno s úkoly a projekty všech osob
	 */
	public void showUkolyProjekty(){
		ukolyProjektyPanel = new ViewUkolyProjekty(mainFrame);
		ukolyProjektyPanel.showView();
	}

	/**
	 * Zobrazí činnosti přihlášené osoby.
	 */
	public void showCinnosti(){
		cinnostiPanel = new ViewCinnosti(mainFrame);
		cinnostiPanel.showView();
	}

	/**
	 * Zobrazí úkoly přihlášené osoby.
	 */
	public void showMojeUkoly(){
		mojeUkolyPanel = new ViewMojeUkoly(mainFrame);
		mojeUkolyPanel.showView();
	}

	/**
	 * Zobrazí přihlašovací okno.
	 */
	public void showPrihlaseni(){
		//Init login panel
		loginPanel = new ViewPrihlaseni(mainFrame);
		views.add(loginPanel);
		loginPanel.showView();
	}

	public static GTDGUI getGTDGUI() {
		return GTDGUI;
	}

	public static Osoba getMyself() {
		return GTDGUI.myself;
	}

	public IOsobaController getOsobaController() {
		return osobaController;
	}

	public ICinnostController getCinnostController() {
		return cinnostController;
	}

	public IKontextController getKontextController() {
		return kontextController;
	}

	public IProjektController getProjektController() {
		return projektController;
	}

	public IUkolController getUkolController() {
		return ukolController;
	}
}