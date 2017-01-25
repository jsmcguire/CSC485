
public class Employee {
	public Employee(int e_id, String e_fname, String e_lname, String e_email, String e_password, int e_type) {
		this.e_id = e_id;
		this.e_fname = e_fname;
		this.e_lname = e_lname;
		this.e_email = e_email;
		this.e_password = e_password;
		this.e_type = e_type;
		this.employeeType = e_type;
	}
	private int e_id;
	private String e_fname;
	private String e_lname;
	private String e_email;
	private String e_password;
	private int e_type;
	public static int employeeType;
	
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public String getE_fname() {
		return e_fname;
	}
	public void setE_fname(String e_fname) {
		this.e_fname = e_fname;
	}
	public String getE_lname() {
		return e_lname;
	}
	public void setE_lname(String e_lname) {
		this.e_lname = e_lname;
	}
	public String getE_email() {
		return e_email;
	}
	public void setE_email(String e_email) {
		this.e_email = e_email;
	}
	public String getE_password() {
		return e_password;
	}
	public void setE_password(String e_password) {
		this.e_password = e_password;
	}
	public int getE_type() {
		return e_type;
	}
	public void setE_type(int e_type) {
		this.e_type = e_type;
	}
}
