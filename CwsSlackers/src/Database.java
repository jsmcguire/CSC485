
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class Database {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    Database() {
        try {

            //MAKE SURE YOU KEEP THE mysql_connector.jar file in java/lib folder
            //ALSO SET THE CLASSPATH
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cwsdb?autoReconnect=true&useSSL=false", "root", "2667");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //ip:username,password
    //return boolean
    public Optional<Employee> checkLogin(String uname, String pwd) {
        try {
        	pst = con.prepareStatement("select * from employee where e_id=? and e_password=?");
            pst.setString(1, uname); //this replaces the 1st  "?" in the query for username
            pst.setString(2, pwd);    //this replaces the 2st  "?" in the query for password
            //executes the prepared statement
            rs = pst.executeQuery();
            if (rs.next()) {
                //TRUE iff the query founds any corresponding data
            	 return Optional.of(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));
            } else {
            	return Optional.empty();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error while validating" + e);
            return Optional.empty();
        }
    }
    
   public Optional<Patron> lookupPatron(String PID) {
	   try{
		   pst = con.prepareStatement("select * from patron where p_id=?");
		   pst.setString(1, PID);
		   rs = pst.executeQuery();
		   if(rs.next()){
			   return Optional.of(new Patron(rs.getInt("p_id"), rs.getString("p_fname"), 
					   rs.getString("p_lname"), rs.getString("p_waiver"), 
					   rs.getString("p_email"), rs.getString("p_belay"), rs.getString("p_suspension")));
					   }
		   else return Optional.empty();
	   }catch(Exception e) {
		   System.out.println("error while looking up patron in the database: " + e);
		   return Optional.empty();
	   }
   }
   
   public boolean checkInPatron( int PID) {
	   try {
		   java.util.Date dt = new java.util.Date();
		   java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		   String date = sdf.format(dt);
		pst = con.prepareStatement("INSERT INTO checkin VALUES ("+ PID+","+date+","+ dt.getTime()+")");
		int result = pst.executeUpdate();
		return true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
   }
   
   public List<CheckInInformation> getCheckInInfo(int PID){
	   List<CheckInInformation> list = new ArrayList<CheckInInformation>();
	   try {
			pst = con.prepareStatement("select * from checkin where p_id=?");
			pst.setLong(1, PID);
			rs = pst.executeQuery();
			while(rs.next()){
				list.add(new CheckInInformation(rs.getInt(1), rs.getString(2), rs.getString(3)));
			} 
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
   }
   
   public void updatePatron(Patron patron){
	   try {
		String query = "INSERT INTO `patron` (`p_id`, `p_fname`, `p_lname`, `p_email`, `p_waiver`, `p_belay`, `p_suspension`) VALUES (?, ?, ?, ?, ?, ?, ?)";
		pst = con.prepareStatement(query);
		pst.setInt(1, patron.getPatronID());
		pst.setString(2, patron.getFirstName());
		pst.setString(3, patron.getLastName());
		pst.setString(4, patron.getEmail());
		pst.setString(5, patron.isWaiver());
		pst.setString(6, patron.getBelay());
		pst.setString(7,patron.isSuspended());
		pst.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}