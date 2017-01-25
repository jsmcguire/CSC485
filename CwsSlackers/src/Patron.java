
public class Patron {
	
	public Patron(int patronID, String firstName, String lastName, String email, String belay, String waiver, String isSuspended) {
		super();
		PatronID = patronID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.waiver = waiver;
		this.email = email;
		this.belay = belay;
		this.isSuspended = isSuspended;
	}
	private int PatronID;
	private String firstName;
	private String lastName;
	private String waiver; 
	private String belay;
	private String email;
	private String isSuspended;
	
	public int getPatronID() {
		return PatronID;
	}
	public void setPatronID(int patronID) {
		PatronID = patronID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String isWaiver() {
		return waiver;
	}
	public void setWaiver(String waiver) {
		this.waiver = waiver;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String isSuspended() {
		return isSuspended;
	}
	public void setSuspended(String isSuspended) {
		this.isSuspended = isSuspended;
	}
	public String getBelay(){
		return this.belay;
	}
	public void setBelay(String belay){
		this.belay = belay;
	}
}

class CheckInInformation {
	public CheckInInformation(int patronID, String date, String time) {
		super();
		this.patronID = patronID;
		this.date = date;
		this.time = time;
	}
	int patronID;
	String date;
	String time;
}
