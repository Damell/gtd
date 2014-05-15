package GTD.PL.PLView;

import GTD.DL.DLEntity.Interval;
import GTD.DL.DLEntity.Task;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 */
public class ViewPlanTask {
	
	UtilDateModel fromModel;
	UtilDateModel toModel;
	JFrame frame;
	Task ukol;

	/**
	 *
	 * @param ukol
	 */
	public ViewPlanTask(Task ukol) {
		getDatesFromUserToPlanTask(ukol);
	}

	void planTask (Task ukol, Interval interval) {
		if(GTDGUI.getGTDGUI().getUkolController().scheduleUkol(ukol, interval)) {
			GTDGUI.getGTDGUI().refresh();
		}
	}


	void getDatesFromUserToPlanTask (Task ukol) {
		Interval interval = null;

		// init date pickers
		fromModel = new UtilDateModel();
		JDatePanelImpl fromDatePanel = new JDatePanelImpl(fromModel);
		JDatePickerImpl fromDatePicker = new JDatePickerImpl(fromDatePanel);
		toModel = new UtilDateModel();
		JDatePanelImpl toDatePanel = new JDatePanelImpl(toModel);
		JDatePickerImpl toDatePicker = new JDatePickerImpl(toDatePanel);
		// set default today's date
		if (ukol.getKalendar().isSet()) {
			fromModel.setValue(ukol.getKalendar().getFrom());
			toModel.setValue(ukol.getKalendar().getTo());
		} else {
			fromModel.setValue(new Date());
			toModel.setValue(new Date());
		}

		JButton planButton = new JButton(Consts.PLAN_TASK);
		planButton.addActionListener(new ActionListener() {
			Task ukol;
			ActionListener setUkol(Task ukol) {
				this.ukol = ukol;
				return this;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fromModel.getValue().before(toModel.getValue())) {
					frame.dispose();
					planTask(ukol, new Interval(fromModel.getValue(), toModel.getValue()));
				} else {
					JOptionPane optionPane = new JOptionPane();
					optionPane.showMessageDialog(frame, Consts.TO_BEFORE_FROM);
				}
			}
		}.setUkol(ukol));

		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel(Consts.SET_INTERVAL));
		panel.add(fromDatePicker);
		panel.add(toDatePicker);
		panel.add(planButton);

		frame = new JFrame(Consts.ACTIVATE_TASK);
		frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible (true);
	}
}
