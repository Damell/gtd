package GTD.PL.PLView;
import GTD.PL.PLController.GTDEventHandler;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

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
	private JList projectsList;
	private JList tasksList;

	public viewUkolyProjekty(mainFrame mainFrame){
		this.mainFrame = mainFrame;
		init();
	}

	void init() {
		setLayout(new GridLayout(2, 1));
		initMenu();
		initMainView();
		add(menu);
		add(mainView);
	}

	void initMenu() {
		menu = new JPanel(new FlowLayout());
	}

	void initMainView() {
		mainView = new JPanel(new FlowLayout());
		projectsList = new JList();
		tasksList = new JList();
		mainView.add(projectsList);
		mainView.add(tasksList);
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