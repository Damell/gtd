package GTD.PL.PLView;
import GTD.BL.BLInterfaces.IOsobaController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Třída představující pohled (okno) s přihlašovacím formulářem.
 * @author Šimon
 * @version 1.0
 * @created 26-4-2014 14:51:24
 */
public class ViewPrihlaseni extends JPanel implements IView {

	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField usernameField;
	JPasswordField passwordField;
	MainFrame mainFrame;
	JButton loginButton;
	IOsobaController osobaCtrl;

	public ViewPrihlaseni(MainFrame mainFrame){
		super();
		osobaCtrl = GTDGUI.getGTDGUI().getOsobaController();
		this.mainFrame = mainFrame;
		init();
	}

	void init() {
		setLayout(new GridLayout(3,2));
		usernameLabel = new JLabel(Consts.USERNAME);
		passwordLabel = new JLabel(Consts.PASSWORD);
		usernameField = new JTextField("pavlim33");
		passwordField = new JPasswordField("oracleGTD");
		loginButton = new JButton(Consts.LOGIN);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(osobaCtrl.loginOsoba(usernameField.getText(), passwordField.getText())) {
					hide();
					GTDGUI.getGTDGUI().showMainWindow();
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(mainFrame, Consts.LOGIN_ERROR);
				}
			}
		});
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(new JPanel());
		add(loginButton);
	}

	/**
	 * Zobrazí daný pohled.
	 */
	public void showView(){
		mainFrame.add(this);
		GTDGUI.getGTDGUI().refresh();
	}

	/**
	 * Smaže daný pohled.
	 */
	public void hide(){
		mainFrame.remove(this);
		GTDGUI.getGTDGUI().refresh();
	}

	/**
	 * Aktualizuje pohled.
	 */
	public void refresh(){

	}
}