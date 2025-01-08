package droneProject_212387575;
import java.util.ArrayList;

public class Manager implements Comparable<Manager>{
	protected String username;
	protected String password;
	protected String id;
	protected String firstName;
	protected String lastName;
	protected ArrayList<Drone> managerDrones = new ArrayList<Drone>();
	private int dronesCount;
	protected ArrayList<Order> managerOrders;
	private int ordersCount;
	
	//building constructor
	public Manager(String id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	//building constructor
	public Manager(String id, String firstName, String lastName, ArrayList<Drone> managerDrones,  ArrayList<Order> managerOrders,String username, String password) {
	    super();
	    this.id = id;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.username=username;
	    this.password=password;
	    
	    if(managerDrones == null) {
	        this.managerDrones = new ArrayList<Drone>();
	        dronesCount = 0;
	    }
	    else {
	        this.managerDrones = managerDrones;
	        dronesCount = managerDrones.size();
	    }
	    
	    if(managerOrders == null) {
	        this.managerOrders = new ArrayList<Order>();
	        ordersCount = 0;
	    }
	    else {
	        this.managerOrders = managerOrders;
	        ordersCount = managerOrders.size();
	    }
	}
	
	//add drone to the manager
	public void addDrone(Drone newDrone)
	{
		if(newDrone == null) {
			System.out.println("ERROR : null drone!!!");
			return;
		}
		for(Drone d : managerDrones) {
			if(d.getDroneCode() == newDrone.getDroneCode()){
				System.out.println("The drone is already in the manager class!");
				return;
			}
		}

		managerDrones.add(newDrone);
		dronesCount++;
		System.out.println("Drone added successfuly to manager class ;) ");
	}
	
	//add order to the manager
	public void addOrder(Order newOrder)
	{
		if(newOrder == null) {
			System.out.println("ERROR : null order!!!");
			return;
		}
		for(Order o : managerOrders) {
			if(o.getOrderCode() == newOrder.getOrderCode()){
				System.out.println("The order is already here!");
				return;
			}
		}

		managerOrders.add(newOrder);
		ordersCount++;
		System.out.println("Order added successfuly ;) ");
	}
	
	// function help us to check if the manager responsible about the drone or not
		public boolean isDroneResponsible(Drone drone) {
			for(Drone d : managerDrones)
				if(d.getDroneCode() == drone.getDroneCode()) return true;
			return false;
		}
	
	

	//getters setters and toString
		public void setPassword(String password) {
			this.password=password;
		}
	public String getPassword() {
			return password;
		}
	public void setUsername(String username) {
			this.username=username;
		}
	public String getUsername() {
			return username;
		}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public ArrayList<Drone> getManagerDrones() {
		return managerDrones;
	}
	public void setManagerDrones( ArrayList<Drone> managerDrones) {
		this.managerDrones = managerDrones;
	}
	public ArrayList<Order> getManagerOrders() {
		return managerOrders;
	}
	public void setManagerOrders(ArrayList<Order> managerOrders) {
		this.managerOrders = managerOrders;
	}

	@Override
	public String toString() {
		return "Manager [username=" + username + ", password=" + password + ", id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", managerDrones=" + managerDrones + ", dronesCount=" + dronesCount
				+ ", managerOrders=" + managerOrders + ", ordersCount=" + ordersCount + "]";
	}
	
	public int compareTo(Manager p) {
		return this.getFirstName().compareTo(p.getFirstName());
	}
	

}
