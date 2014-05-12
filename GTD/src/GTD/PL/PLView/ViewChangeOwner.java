/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GTD.PL.PLView;

import GTD.DL.DLEntity.Aktivita;
import GTD.DL.DLEntity.Osoba;
import GTD.DL.DLEntity.Projekt;
import GTD.DL.DLEntity.Ukol;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author damell
 */
public class ViewChangeOwner {
	
	JFrame frame;
	Aktivita activity;
	Osoba osoba;
	JList usersList;
	List<Osoba> users;

	public ViewChangeOwner(Aktivita activity) {
		this.activity = activity;
		setUser();
	}

	void changeOwner () {
		if (activity instanceof Ukol) {
			GTDGUI.getGTDGUI().getUkolController().changeOwner((Ukol) activity, osoba);
		} else {
			GTDGUI.getGTDGUI().getProjektController().changeOwner((Projekt) activity, osoba);
		}
		GTDGUI.getGTDGUI().refresh();
	}


	void setUser () {
		users = GTDGUI.getGTDGUI().getOsobaController().getAllUsers();
		usersList = new JList(users.toArray());
		usersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usersList.setVisibleRowCount(-1);
		JScrollPane usersScrollPane = new JScrollPane(usersList);
		usersScrollPane.setPreferredSize(new Dimension(200, 200));

		JButton changeOwnerButton = new JButton(Consts.SET_OWNER);
		changeOwnerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = usersList.getSelectedIndex();
				if (selectedIndex != -1) {
					osoba = users.get(selectedIndex);
					frame.dispose();
					changeOwner();
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(frame, Consts.NO_USER_SELECTED);
				}
			}
		});

		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel(Consts.SET_OWNER));
		panel.add(usersScrollPane);
		panel.add(changeOwnerButton);

		frame = new JFrame(Consts.ACTIVATE_TASK);
		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible (true);
	}
}
