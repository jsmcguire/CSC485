import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SecondCheckIn extends JFrame {

	private JPanel contentPane;
	private JTextField p_checkinField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SecondCheckIn frame = new SecondCheckIn();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public SecondCheckIn() {
		Database db = new Database();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Check In Patron:");
		label.setBounds(35, 79, 117, 16);
		contentPane.add(label);
		
		p_checkinField = new JTextField();
		p_checkinField.setColumns(10);
		p_checkinField.setBounds(151, 74, 209, 26);
		contentPane.add(p_checkinField);
		
		JButton button = new JButton("Check In");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(db.lookupPatron(p_checkinField.getText()).isPresent()){
					db.checkInPatron(Integer.valueOf(p_checkinField.getText().trim()));
					p_checkinField.setText("");
				}
				else{
					JOptionPane.showMessageDialog(null, "Wrong Patron ID!");
				}
			}
		});
		button.setBounds(151, 148, 117, 29);
		contentPane.add(button);
	}

}
