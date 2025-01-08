package droneProject_212387575;
import java.util.ArrayList;

public class ChiefManager extends Manager {
	private String userName;
	private String password;
	
	//building constructor
	public ChiefManager(String id, String firstName, String lastName, ArrayList<Drone> managerDrones, ArrayList<Order> managerOrders,
			String userName, String password) {
		super(id, firstName, lastName, managerDrones, managerOrders, password, password);
		this.userName = userName;
		this.password = password;
	}
	
	public ChiefManager( String firstName, String lastName,String id,String userName, String password) {
		super(id, firstName, lastName);
		this.userName = userName;
		this.password = password;
	}
	
    //getters and setters
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ChiefManager [userName=" + userName + ", password=" + password + ", id=" + id + ", firstName="
				+ firstName + ", lastName=" + lastName + ", managerDrones=" + managerDrones + ", managerOrders="
				+ managerOrders + "]";
	}
	

}
