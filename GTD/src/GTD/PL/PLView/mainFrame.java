/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GTD.PL.PLView;

import GTD.DL.DLDAO.DatabaseConnection;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author damell
 */
public class mainFrame extends JFrame implements WindowListener {

	private JTabbedPane tabbedPane;
	
	mainFrame(String appTitle) {
		super(appTitle);
		
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setSize(900, 700);
		setVisible(true);
		addWindowListener(this);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
	}

	void refresh() {
		revalidate();
		repaint();
	}

	void addTab(String title, JPanel panel) {
		if(tabbedPane.getTabCount() == 0) {
			setLayout(new BorderLayout());
			add(tabbedPane);
		}
		tabbedPane.add(title, panel);
		refresh();
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		DatabaseConnection.closeConnection();
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}
