package GTD.PL.PLView;

import GTD.DL.DLEntity.Action;
import GTD.DL.DLEntity.Person;
import GTD.DL.DLEntity.Project;
import GTD.DL.DLEntity.Task;
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
 */
public class ViewChangeOwner {
	
	JFrame frame;
	Action activity;
	Person osoba;
	JList usersList;
	List<Person> users;

	/**
	 *
	 * @param activity
	 */
	public ViewChangeOwner(Action activity) {
		this.activity = activity;
		setUser();
	}

	void changeOwner () {
		if (activity instanceof Task) {
			GTDGUI.getGTDGUI().getUkolController().changeOwner((Task) activity, osoba);
		} else {
			GTDGUI.getGTDGUI().getProjektController().changeOwner((Project) activity, osoba);
		}
		GTDGUI.getGTDGUI().refresh();
	}


	void setUser () {
		if (activity instanceof Project) {
			users = ((Project) activity).getSkupina();
		} else if (((Task) activity).getProjekt() == 0) {
			users = GTDGUI.getGTDGUI().getOsobaController().getAllUsers();
		} else {
			users = GTDGUI.getGTDGUI().getProjektController().getProjekt(((Task) activity).getProjekt()).getSkupina();
		}
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

		frame = new JFrame(Consts.CHANGE_OWNER);
		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible (true);
	}
}
