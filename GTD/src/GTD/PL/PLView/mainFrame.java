/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GTD.PL.PLView;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 *
 * @author damell
 */
public class mainFrame extends JFrame {
	
	mainFrame(String appTitle) {
		super(appTitle);
		
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setSize(300, 300);
		setVisible(true);
	}

	void refresh() {
		revalidate();
		repaint();
	}
}
