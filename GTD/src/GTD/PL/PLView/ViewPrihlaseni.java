package GTD.PL.PLView;
import GTD.BL.BLInterfaces.IOsobaController;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Třída představující pohled (okno) s přihlašovacím formulářem.
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ViewPrihlaseni extends JPanel implements IView {

	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField usernameField;
	JPasswordField passwordField;
	MainFrame mainFrame;
	JButton loginButton;
	IOsobaController osobaCtrl;

	/**
	 *
	 * @param mainFrame
	 */
	public ViewPrihlaseni(MainFrame mainFrame){
		super();
		osobaCtrl = GTDGUI.getGTDGUI().getOsobaController();
		this.mainFrame = mainFrame;
		init();
	}

	void init() {
		setLayout(new GridBagLayout());
		usernameLabel = new JLabel(Consts.USERNAME);
		passwordLabel = new JLabel(Consts.PASSWORD);
		usernameField = new JTextField("pavlim33");
		passwordField = new JPasswordField("oracleGTD");
		loginButton = new JButton(Consts.LOGIN);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(osobaCtrl.loginOsoba(usernameField.getText(), new String(passwordField.getPassword()))) {
					hideView();
					GTDGUI.getGTDGUI().showMainWindow();
				} else {
					JOptionPane optionPane = new JOptionPane();
					JOptionPane.showMessageDialog(mainFrame, Consts.LOGIN_ERROR);
				}
			}
		});
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(160,10,50,10);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		JLabel title = new JLabel(Consts.APP_TITLE);
		title.setFont(title.getFont().deriveFont(40f));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title, c);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipady = 10;
		c.insets = new Insets(5,80,5,10);
		add(usernameLabel, c);
		c.gridx = 2;
		c.insets = new Insets(5,10,5,10);
		c.ipadx = 30;
		add(usernameField, c);
		c.gridx = 1;
		c.gridy++;
		c.insets = new Insets(5,80,5,10);
		c.ipadx = 0;
		add(passwordLabel, c);
		c.gridx = 2;
		c.insets = new Insets(5,10,5,10);
		c.ipadx = 30;
		add(passwordField, c);
		c.gridx = 1;
		c.gridy++;
		c.gridwidth = 2;
		c.insets = new Insets(5,75,5,10);
		c.ipady = 10;
		add(loginButton, c);
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
	public void hideView(){
		mainFrame.remove(this);
		GTDGUI.getGTDGUI().refresh();
	}

	/**
	 * Aktualizuje pohled.
	 */
	public void refresh(){

	}
}