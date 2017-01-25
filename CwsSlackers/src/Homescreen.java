import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Homescreen extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4471032929381136658L;
	private JPanel contentPane;
	private JTextField patronID;
	private Patron currentPatron;
	protected Employee employee ;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField p_checkinField;
	private JTextField p_fname_edit;
	private JTextField p_lname_edit;
	private JTextField p_email_edit;
	private JTextField p_id_edit;
	private JTextField employeeID;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homescreen frame = new Homescreen();
					frame.setVisible(true);
					frame.setAlwaysOnTop(true);
					SecondCheckIn sc = new SecondCheckIn();
					sc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Homescreen() {
		Database db = new Database();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 579, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel Login = new JPanel();
		contentPane.add(Login, "name_124086143310474");
		Login.setLayout(null);
		Login.setVisible(true);
		
		JPanel homeScreen = new JPanel();
		contentPane.add(homeScreen, "name_20426409416021");
		homeScreen.setLayout(null);
		
		JPanel patronScreen = new JPanel();
		contentPane.add(patronScreen, "name_20478681160761");
		patronScreen.setLayout(null);
		
		JLabel lblHomeScreen = new JLabel("Home Screen");
		lblHomeScreen.setBounds(228, 38, 143, 16);
		homeScreen.add(lblHomeScreen);
		
		JPanel createPatron = new JPanel();
		contentPane.add(createPatron, "name_19416900123726");
		createPatron.setLayout(null);
		
		JLabel label = new JLabel("Climbing Wall System");
		label.setBounds(206, 37, 147, 16);
		Login.add(label);
		
		JLabel label_1 = new JLabel("Employee ID: ");
		label_1.setBounds(79, 122, 86, 16);
		Login.add(label_1);
		
		employeeID = new JTextField();
		employeeID.setColumns(10);
		employeeID.setBounds(206, 117, 208, 26);
		Login.add(employeeID);
		
		JLabel label_2 = new JLabel("Password: ");
		label_2.setBounds(79, 214, 86, 16);
		Login.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(206, 209, 205, 26);
		Login.add(passwordField);
		
		JButton button_2 = new JButton("Login");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] temp_pwd=passwordField.getPassword(); 
                String pwd=null;
                pwd=String.copyValueOf(temp_pwd);
                Optional<Employee> empl = db.checkLogin(employeeID.getText().trim(), pwd);
              if(empl.isPresent())
                {
                   Login.setVisible(Boolean.FALSE);
                   homeScreen.setVisible(Boolean.TRUE);
                   employee = empl.get();
                   Employee.employeeType = empl.get().getE_type();
                }
                else
                {
                	//a pop-up box
                    JOptionPane.showMessageDialog(null, "Login failed!","Wrong ID or Password.",
                            JOptionPane.ERROR_MESSAGE);
                }
			}
		});
		button_2.setBounds(206, 312, 117, 29);
		Login.add(button_2);
		
		
		JLabel lblPatronScreen = new JLabel("Patron Information");
		lblPatronScreen.setBounds(229, 42, 166, 16);
		patronScreen.add(lblPatronScreen);
		
		JLabel lblPatronName = new JLabel("First Name:");
		lblPatronName.setBounds(66, 165, 72, 16);
		patronScreen.add(lblPatronName);
		
		JLabel p_fname = new JLabel("");
		p_fname.setBounds(178, 165, 191, 16);
		patronScreen.add(p_fname);
		
		JDateChooser p_waiver_edit = new JDateChooser();
		p_waiver_edit.setBounds(185, 272, 179, 26);
		createPatron.add(p_waiver_edit);
		
		JDateChooser p_suspension_edit = new JDateChooser();
		p_suspension_edit.setBounds(185, 321, 179, 26);
		createPatron.add(p_suspension_edit);
		
		JDateChooser p_belay_edit = new JDateChooser();
		p_belay_edit.setBounds(185, 218, 179, 26);
		createPatron.add(p_belay_edit);
		
		JPanel patronCheckinHistory = new JPanel();
		contentPane.add(patronCheckinHistory, "name_121395561604024");
		patronCheckinHistory.setLayout(null);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(60, 39, 433, 373);
		patronCheckinHistory.add(editorPane);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patronCheckinHistory.setVisible(Boolean.FALSE);
				patronScreen.setVisible(Boolean.TRUE);
			}
		});
		btnBack.setBounds(213, 441, 117, 29);
		patronCheckinHistory.add(btnBack);
		Document doc = editorPane.getDocument();
		
		JButton btnCheckInPatron = new JButton("Check In Patron");
		btnCheckInPatron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.checkInPatron(currentPatron.getPatronID());
			}
		});
		btnCheckInPatron.setBounds(52, 335, 157, 29);
		patronScreen.add(btnCheckInPatron);
		
		JButton btnCheckInHistory = new JButton("Check In History");
		btnCheckInHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<CheckInInformation> list = new ArrayList<>();
				patronScreen.setVisible(Boolean.FALSE);
				patronCheckinHistory.setVisible(Boolean.TRUE);
				list = db.getCheckInInfo(currentPatron.getPatronID());
				list.forEach(info -> {
					String str = (info.patronID +"  "+info.date+"  "+info.time+"\n");
					System.out.println(str);
					try {
						doc.insertString(doc.getLength(), str,null);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				});
			}
		});
		btnCheckInHistory.setBounds(350, 335, 140, 29);
		patronScreen.add(btnCheckInHistory);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setBounds(66, 223, 61, 16);
		patronScreen.add(lblNewLabel_1);
		
		JLabel lblWaiverExperiationDate = new JLabel("Waiver Experiation Date: ");
		lblWaiverExperiationDate.setBounds(66, 251, 171, 16);
		patronScreen.add(lblWaiverExperiationDate);
		
		JLabel lblBelayExperiationDate = new JLabel("Belay Experiation Date: ");
		lblBelayExperiationDate.setBounds(66, 279, 171, 16);
		patronScreen.add(lblBelayExperiationDate);
		
		JLabel lblSuspensionExperiationDate = new JLabel("Suspension Experiation Date: ");
		lblSuspensionExperiationDate.setBounds(66, 307, 200, 16);
		patronScreen.add(lblSuspensionExperiationDate);
		
		JLabel p_email = new JLabel("");
		p_email.setBounds(194, 223, 296, 16);
		patronScreen.add(p_email);
		
		JPanel adminPanel = new JPanel();
		contentPane.add(adminPanel, "name_12036479781055");
		adminPanel.setLayout(null);
		
		JLabel p_waiver = new JLabel("");
		p_waiver.setBounds(229, 251, 296, 16);
		patronScreen.add(p_waiver);
		
		JLabel p_belay = new JLabel("");
		p_belay.setBounds(226, 279, 296, 16);
		patronScreen.add(p_belay);
		
		JLabel p_suspension = new JLabel("");
		p_suspension.setBounds(263, 307, 259, 16);
		patronScreen.add(p_suspension);
		
		JLabel p_id = new JLabel("");
		p_id.setBounds(178, 137, 191, 16);
		patronScreen.add(p_id);
		
		JLabel p_lname = new JLabel("");
		p_lname.setBounds(178, 195, 191, 16);
		patronScreen.add(p_lname);
		
		JButton btnNewButton = new JButton("Back to Homescreen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patronScreen.setVisible(Boolean.FALSE);
				homeScreen.setVisible(Boolean.TRUE);
			}
		});
		btnNewButton.setBounds(206, 453, 166, 29);
		patronScreen.add(btnNewButton);
		
		JButton btnEditInfo = new JButton("Edit Info");
		btnEditInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patronScreen.setVisible(false);
				createPatron.setVisible(true);
				p_id_edit.setText(p_id.getText());
				p_fname_edit.setText(p_fname.getText());
				p_lname_edit.setText(p_lname.getText());
				p_email_edit.setText(p_email.getText());
				p_belay_edit.setDateFormatString(p_belay.getText()); 
				p_waiver_edit.setDateFormatString(p_waiver.getText());
				p_suspension_edit.setDateFormatString(p_suspension.getText());
			}
		});
		btnEditInfo.setBounds(221, 335, 117, 29);
		patronScreen.add(btnEditInfo);
		
		JLabel lblLastName_1 = new JLabel("Last Name:");
		lblLastName_1.setBounds(66, 195, 72, 16);
		patronScreen.add(lblLastName_1);
		
		JLabel lblPatronId_1 = new JLabel("Patron ID: ");
		lblPatronId_1.setBounds(66, 137, 72, 16);
		patronScreen.add(lblPatronId_1);
		
		JButton btnPatronView = new JButton("Patron View");
		btnPatronView.setBounds(397, 126, 117, 29);
		homeScreen.add(btnPatronView);
		
		patronID = new JTextField();
		patronID.setBounds(176, 126, 209, 26);
		homeScreen.add(patronID);
		patronID.setColumns(10);
		
		JLabel lblSearchForPatron = new JLabel("Search for Patron: ");
		lblSearchForPatron.setBounds(53, 131, 117, 16);
		homeScreen.add(lblSearchForPatron);
		
		JButton btnAdminView = new JButton("Admin View");
		btnAdminView.setVisible(true);
		btnAdminView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Employee.employeeType == 0){
				homeScreen.setVisible(false);
				adminPanel.setVisible(true);
				}
			}
		});
		btnAdminView.setBounds(371, 319, 143, 29);
		homeScreen.add(btnAdminView);
		
		JButton btnCreatePatron = new JButton("Create Patron");
		btnCreatePatron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeScreen.setVisible(false);
				createPatron.setVisible(true);
			}
		});
		btnCreatePatron.setBounds(53, 240, 143, 29);
		homeScreen.add(btnCreatePatron);
		
		JButton btnCalendar = new JButton("Calendar");
		btnCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCalendar.setBounds(371, 240, 143, 29);
		homeScreen.add(btnCalendar);
		
		JButton btnNotes = new JButton("Notes");
		btnNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNotes.setBounds(53, 319, 143, 29);
		homeScreen.add(btnNotes);
		
		JLabel lblCheckInPatron = new JLabel("Check In Patron:");
		lblCheckInPatron.setBounds(53, 175, 117, 16);
		homeScreen.add(lblCheckInPatron);
		
		p_checkinField = new JTextField();
		p_checkinField.setBounds(176, 170, 209, 26);
		homeScreen.add(p_checkinField);
		p_checkinField.setColumns(10);
		
		JButton button_1 = new JButton("Check In");
		button_1.addActionListener(new ActionListener() {
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
		button_1.setBounds(397, 170, 117, 29);
		homeScreen.add(button_1);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeScreen.setVisible(false);
				Login.setVisible(true);
			}
		});
		btnLogOut.setBounds(235, 414, 117, 29);
		homeScreen.add(btnLogOut);
		
		JLabel lblEmployeeId = new JLabel("Employee ID: ");
		lblEmployeeId.setBounds(46, 106, 86, 16);
		adminPanel.add(lblEmployeeId);
		
		textField = new JTextField();
		textField.setBounds(140, 101, 242, 26);
		adminPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnLookupEmployee = new JButton("Lookup Employee");
		btnLookupEmployee.setBounds(397, 101, 143, 29);
		adminPanel.add(btnLookupEmployee);
		
		JButton btnCreateNewEmployee = new JButton("Create New Employee");
		btnCreateNewEmployee.setBounds(46, 286, 179, 29);
		adminPanel.add(btnCreateNewEmployee);
		
		JButton btnCreateNewPatron = new JButton("Change Admin Password");
		btnCreateNewPatron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateNewPatron.setBounds(46, 354, 179, 29);
		adminPanel.add(btnCreateNewPatron);
		
		JButton button = new JButton("Create New Patron");
		button.setBounds(358, 286, 179, 29);
		adminPanel.add(button);
		
		JLabel lblPatronId = new JLabel("Patron ID: ");
		lblPatronId.setBounds(46, 194, 117, 16);
		adminPanel.add(lblPatronId);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(140, 189, 242, 26);
		adminPanel.add(textField_1);
		
		JButton btnLookupPatron = new JButton("Lookup Patron");
		btnLookupPatron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLookupPatron.setBounds(397, 189, 140, 29);
		adminPanel.add(btnLookupPatron);
		
		JLabel lblNewLabel = new JLabel("Administrator View");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(185, 28, 197, 16);
		adminPanel.add(lblNewLabel);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adminPanel.setVisible(false);
				homeScreen.setVisible(true);
			}
		});
		btnBack_1.setBounds(227, 430, 117, 29);
		adminPanel.add(btnBack_1);
		
		JButton button_3 = new JButton("Create/Edit Course");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_3.setBounds(358, 354, 179, 29);
		adminPanel.add(button_3);
		
		JLabel lblFirstName = new JLabel("First Name: ");
		lblFirstName.setBounds(53, 109, 89, 16);
		createPatron.add(lblFirstName);
		
		p_fname_edit = new JTextField();
		p_fname_edit.setBounds(154, 104, 210, 26);
		createPatron.add(p_fname_edit);
		p_fname_edit.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setBounds(53, 147, 89, 16);
		createPatron.add(lblLastName);
		
		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setBounds(53, 185, 89, 16);
		createPatron.add(lblEmail);
		
		p_lname_edit = new JTextField();
		p_lname_edit.setColumns(10);
		p_lname_edit.setBounds(154, 142, 210, 26);
		createPatron.add(p_lname_edit);
		
		p_email_edit = new JTextField();
		p_email_edit.setColumns(10);
		p_email_edit.setBounds(154, 180, 210, 26);
		createPatron.add(p_email_edit);
		
		p_id_edit = new JTextField();
		p_id_edit.setColumns(10);
		p_id_edit.setBounds(154, 66, 210, 26);
		createPatron.add(p_id_edit);
		
		JLabel lblBelay = new JLabel("Belay:");
		lblBelay.setBounds(53, 228, 89, 16);
		createPatron.add(lblBelay);
		
		JLabel lblWaiver = new JLabel("Waiver:");
		lblWaiver.setBounds(53, 282, 89, 16);
		createPatron.add(lblWaiver);
		
		JLabel lblSuspension = new JLabel("Suspension:");
		lblSuspension.setBounds(53, 331, 89, 16);
		createPatron.add(lblSuspension);
		
		JLabel lblAddeditEmployees = new JLabel("Add/Edit Patrons");
		lblAddeditEmployees.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAddeditEmployees.setBounds(202, 27, 162, 16);
		createPatron.add(lblAddeditEmployees);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createPatron.setVisible(false);
				homeScreen.setVisible(true);
			}
		});
		btnCancel.setBounds(89, 385, 117, 29);
		createPatron.add(btnCancel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.updatePatron(new Patron(Integer.valueOf(p_id_edit.getText().trim()), 
						p_fname_edit.getText(), p_lname_edit.getText(), 
						p_email_edit.getText(), 
						p_belay_edit.getDate().toString(), 
						p_waiver_edit.getDate().toString(), 
						p_suspension_edit.getDate().toString()));
			}
		});
		btnSubmit.setBounds(355, 385, 117, 29);
		createPatron.add(btnSubmit);
		
		JLabel sdsd = new JLabel("ID: ");
		sdsd.setBounds(53, 71, 89, 16);
		createPatron.add(sdsd);
		
		JPanel calendar = new JPanel();
		contentPane.add(calendar, "name_128661447565387");
		calendar.setLayout(null);
		
		btnPatronView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(db.lookupPatron(patronID.getText()).isPresent()){
					currentPatron = db.lookupPatron(patronID.getText()).get();
					patronID.setText("");
					homeScreen.setVisible(Boolean.FALSE);
					patronScreen.setVisible(Boolean.TRUE);
					p_id.setText(String.valueOf(currentPatron.getPatronID()));
					p_fname.setText(currentPatron.getFirstName());
					p_lname.setText(currentPatron.getLastName());
					p_email.setText(currentPatron.getEmail());
					p_waiver.setText(currentPatron.isWaiver());
					p_belay.setText(currentPatron.getBelay());
					p_suspension.setText(currentPatron.isSuspended());
				} else {
					JOptionPane.showMessageDialog(null, "Wrong Patron ID!");
				}
			}
		});
	}
}
