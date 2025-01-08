package droneProject_212387575;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;

public class systemDataBase {
	
	public static ChiefManager ChiefManager;
	public static ArrayList<Manager> managerList = new ArrayList<Manager>();
	public static Hashtable<Integer, Subscription> subscribers = new Hashtable <Integer, Subscription>();
	public static ArrayList<Order> orders = new ArrayList<Order>();
	public static ArrayList<Drone> drones = new ArrayList<Drone>();
	public static Hashtable<Integer,ArrayList<Order>> table = new Hashtable<Integer,ArrayList<Order>>();
	public static ArrayList<Subscription> sub = new ArrayList<Subscription>();
	
    ChiefManager mainAdmin1= new ChiefManager("1", "Ross", "Geller", null, null, "system", "12345");

	
	//method to add order to the table and orders list
	public void addOrderToTable(Order order) {
		if(order==null) {//if there is no order to add
			System.out.println("There is no order to add");
			return;
		}
		if(table.contains(order)) {//check if order already exists
			System.out.println("The order already exist in the order table;");
			return;
		}
		addOrderToOrders(order);//add the order to the orders too
		table.put(order.getSubCode(), orders);//add the table hashtable
		System.out.println("The order was also updated in the table");
	}
	
	//method to add order to the orders list
	public void addOrderToOrders(Order order) {
		if(order==null) {//if there is no order
			System.out.println("There is no order to add!");
			return;
		}
		for(Order o:orders) {//check if the order already exists
			if(o.getOrderCode()==order.getOrderCode()) {//if the order exists in the orders arrayList
				System.out.println("The order already exists in the order list");
			}
		}
		//add the order to the orders list
		orders.add(order);
		System.out.println("The order was added to the orders list");
		return;
	}
	
	//method to add order to manager list
	public void addOrderToManager(Order order, Manager manager) {
		for(Manager m: managerList) {
			if(m.getId().equals(manager.getId())) {//we found the manager that wants to make the order
			for(Order o :m.getManagerOrders()) {
				if(o.getOrderCode()== order.getOrderCode()) {//if the order already exist
					System.out.println("The order already exists for this manager");
					return;
				}
			}
			}
		}
		for(Manager m: managerList) {
			if(m.getId().equals(manager.getId())) {
				m.addOrder(order);
				System.out.println("The order was added to the managers orders");
				return;		
			}
			
		}
		System.out.println("The manager was not found!");
		return;
		
		
	}
	
	//method to add manager to the drones list
	public void addManagerToDrone(Manager manager) {
		if(manager==null) {
			System.out.println("There is no manager to add");
		}
		
		for(Drone d: drones) {//check if manager already exists in drone list
			for(Manager m: d.managers) {
				if(m.getId().equals(manager.getId())) {
					System.out.println("The drone already has this manager responsible for it!");
					return;
				}
			}
		}
		
		for(Drone d: drones) {
			if(d.managers== null) {//check if there is no manager responsible for the drone
				d.managers = new ArrayList<Manager>();//initialize the mangers arrayList in drones
				d.addManager(manager);
				
			}
			else {
				d.managers.add(manager);
			}
		}
		System.out.println("The manager was added to the drone");
		
	}
	
	//method to add drone to the manager list
	public void addDroneToManager(Drone drone) {
		if(drone == null) {//if we didn't receive  a drone to add 
			System.out.println("There is no drone to add");
			return;
		}
		
		for(Manager m: managerList) {//check if manager is already responsible for drone
			for(Drone d: m.getManagerDrones()) {
				if(d.getDroneCode()==drone.droneCode) {
					System.out.println("The manager is already responsible for this drone!");
					System.out.println(d);//check
					System.out.println(m);//check
					return;
				}
			}
		}
		
		for(Manager m: managerList) {
			if(m.getManagerDrones() == null) {//if the manager doesn't have any drones
				m.setManagerDrones(new ArrayList<>());//initialize the drones list in managers
				m.addDrone(drone);
				System.out.println("The drone was added to the manager");

			}
			else {
			m.getManagerDrones().add(drone);
			System.out.println("The drone was added to the manager");
			return;
			}
		}
		
	}
	
	//method to add a new drone to the drones list only
	public void addDrone(Drone drone) {
		if(drones == null) {//if the drones list was empty
			drones = new ArrayList<>();
			
		}
		
		for(Drone d: drones) {//check if drone already exists
			if(d.getDroneCode()== drone.getDroneCode()) {
				System.out.println("The drone already exist in the drone list in the data base!");
				return;
			}
		}
		
			
				drones.add(drone);//add new drone to the drones list
		
		System.out.println("The drone was added successfuly to drone list in system data base");
	}
	
	
	//chief manager adding a new drone to his list and db drones list
	public void addDrone(Drone drone, String id) {
		if(drones == null) {//if the drones list was empty
			drones = new ArrayList<>();
			
		}
		
		for(Drone d: drones) {//check if drone already exists
			if(d.getDroneCode()== drone.getDroneCode()) {
				System.out.println("The drone already exist in the drones list!");
				return;
			}
		}
		for(Manager m:managerList) {
			if(m.getId().equals(id)) {
				drones.add(drone);//add new drone to the drones list
				m.addDrone(drone);//adding the drone to the managers drone
			}
		}
		
		System.out.println("The drone was added successfuly to the managers drones and drones list");
		
	}
	
	public void addSubscriber(Subscription newSub){//chief manager adding a new subscriber
		if(subscribers == null) {//if the list was empty
			subscribers = new Hashtable<Integer, Subscription>();
			subscribers.put(newSub.getSubCode(), newSub);
			System.out.println("The subscriber was added successfully!");
			return;

		}
		
		
		for (Map.Entry<Integer, Subscription> entry : subscribers.entrySet()) {//if the list isn't empty check if subscriber already exists
			  Subscription existingSub = entry.getValue(); 
			  if (existingSub.equals(newSub)) {
				System.out.println("Subscriber already exists!");
				return;
			}
		}
		
		subscribers.put(newSub.getSubCode(), newSub);
		System.out.println("The subscriber was added successfully!");
		return;
		
	}
	
	//method to add manager to the managers list
	public void addManager(Manager manager) {
		
		for(Manager m: managerList) {//check if manager exists
			if(m.getId().equals(manager.getId())) {
				System.out.println("Manager already exists!");
				return;
			}
		}
		managerList.add(manager);
		System.out.println("Manager was added successfuly!");
	}
	
	//empty constructor
	public systemDataBase() {
		
	}
	
	//constructor
	public systemDataBase(String username, String password,String firstName, String lastName,String id) {
		    ChiefManager = new ChiefManager(username,password,firstName,lastName,id);
		drones = new ArrayList<Drone>();
		orders = new ArrayList<Order>();
		managerList = new ArrayList<Manager>();
		subscribers = new Hashtable <Integer, Subscription>();
		table =  new Hashtable<Integer,ArrayList<Order>>();
		if(managerList ==null) {
			managerList = new ArrayList<Manager>();
			managerList.add(systemDataBase.ChiefManager);
		}
		else
		managerList.add(ChiefManager);
		
	}
	
	//constructor
     public systemDataBase(ArrayList<Manager> managerList) {
    	 systemDataBase.managerList=managerList;
		
	}
	
   //method that gets the sub code and returns all the orders that he has
	public ArrayList<Order> subscriberOrder(int subCode) {
		ArrayList<Order> subOrders = new ArrayList<Order>();
		
		for(Order o: orders) {
			if(o.getSubCode() == subCode) {
				subOrders.add(o);
			}
			
		}
		return subOrders;
		
	}
	
	//method to return all the drones the manager is responsible for 
	public ArrayList<Drone> managerOrders(Manager manager){
		ArrayList<Drone> managerDrones = new ArrayList<Drone>();
		
		for(Manager m: managerList) {
			if(m.getId() == manager.getId()) {
				managerDrones.addAll(m.getManagerDrones());	
				break;
			}
		}
		return managerDrones;
		
	}
	
	//method that returns all the managers that are responsible for the drone
	public ArrayList<Manager> ManagerDrones(Drone drone){
		ArrayList<Manager> managers = new ArrayList<Manager>();
	
		
		for(Manager m: managerList) {
			if(m.getManagerDrones().contains(drone)) {
				managers.add(m);
			
		}
		
	}
		return managers;
	}

	//getters and setters and toString
	public ChiefManager getChiefManager() {
		return this.ChiefManager;
	}

	public void setChiefManager(ChiefManager chiefManager) {
		this.ChiefManager = chiefManager;
	}

	public ArrayList<Manager> getManagerList() {
		return managerList;
	}

	public void setManagerList(ArrayList<Manager> managerList) {
		this.managerList = managerList;
	}

	public Hashtable<Integer, Subscription> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(Hashtable<Integer, Subscription> subscribers) {
		this.subscribers = subscribers;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Drone> getDrones() {
		return drones;
	}
	public Drone getDrone(int index){
		return drones.get(index);
	}

	public void setDrones(ArrayList<Drone> drones) {
		this.drones = drones;
	}

	public Hashtable<Integer, ArrayList<Order>> getTable() {
		return table;
	}

	public void setTable(Hashtable<Integer, ArrayList<Order>> table) {
		this.table = table;
	}

	@Override
	public String toString() {
		return "systemDataBase [ChiefManager=" + ChiefManager + ", managerList=" + managerList + ", subscribers="
				+ subscribers + ", orders=" + orders + ", drones=" + drones + ", table=" + table + "]";
	}
	
	/*public static void main(String[] args) {
	       ChiefManager mainAdmin1= new ChiefManager("1", "Ross", "Geller", null, null, "system", "12345");

	}*/
	
	public static void main(String[] args) {
		
		// Create an instance of systemDataBase
	   
	    
	  
	        for (Entry<Integer, Subscription> entry :  systemDataBase.subscribers.entrySet()) {
	            systemDataBase.sub.add(entry.getValue());
	        }
	        
	        // Sort the sub ArrayList by familyName
	        Collections.sort( systemDataBase.sub);
	        //sort the manager list by first name
	        Collections.sort(systemDataBase.managerList);
	       
	}

	public Manager findManagerById(int id) {
		   for(Manager m : systemDataBase.managerList) {
			   if(Integer.parseInt(m.getId())== id) {
				   return m;
			   }
		   }
		   return null;
	}

	public Drone findDroneByCode(int code) {
		for(Drone d :systemDataBase.drones) {
			if(d.getDroneCode() == code) {
				return d;
			}
		}
		return null;
	}
	public static int generateOrderCode() {
	    // You can use a combination of current timestamp and a random number for uniqueness
	    long timestamp = System.currentTimeMillis();
	    int random = (int) (Math.random() * 1000); // You can adjust the range as needed
	    return (int) (timestamp + random);
	}
	// Helper method to calculate the order price based on the drone type
		public static double calculateExtraOrderPrice(Drone drone) {
		    if (drone instanceof ExpressDrone) {
		        ExpressDrone expressDrone = (ExpressDrone) drone;
		        return expressDrone.getExtraPrice(); 
		    } else if (drone instanceof DistanceDrone) {
		        DistanceDrone distanceDrone = (DistanceDrone) drone;
		        return distanceDrone.getExtraPrice(); 
		    } else {
		        // Default pricing for general drones
		        return 0.0; 
		    }
		}
}
