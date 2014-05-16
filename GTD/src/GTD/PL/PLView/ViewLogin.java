package GTD.PL.PLView;
import GTD.BL.BLInterfaces.IPersonController;
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
 * The login view
 * @author GTD team
 * @version 1.0
 */
public class ViewLogin extends JPanel implements IView {

	static final long serialVersionUID = 1L;
	private Config config;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private MainFrame mainFrame;
	private JButton loginButton;
	private IPersonController personCtrl;

	/**
	 *
	 * @param mainFrame
	 */
	public ViewLogin(MainFrame mainFrame, Config config){
		super();
		this.config = config;
		this.personCtrl = GTDGUI.getGTDGUI().getPersonController();
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
				if(personCtrl.loginPerson(usernameField.getText(), new String(passwordField.getPassword()), config.getHostname(), config.getPort(), config.getSID())) {
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

	@Override
	public void showView(){
		mainFrame.add(this);
		GTDGUI.getGTDGUI().refresh();
	}

	/**
	 * Hides this view
	 */
	public void hideView(){
		mainFrame.remove(this);
		GTDGUI.getGTDGUI().refresh();
	}

	@Override
	public void refresh(){

	}
}