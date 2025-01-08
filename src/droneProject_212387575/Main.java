package droneProject_212387575;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
	private static int count=0;
	private static systemDataBase currentSystem = new systemDataBase();
	public static void main(String[] args) {
	
		if(count == 0) currentSystem = initializeSystem();
		displayMainMenu();
        count++;

		Scanner scanner = new Scanner(System.in);
		int choice ;
        choice = scanner.nextInt();
        while(choice<1 || choice>3) {
        	System.out.println("Invalid choice. Please enter a valid option.");
            displayMainMenu();
            choice = scanner.nextInt();
        }
		if(choice == 1)  ChiefManagerLogin(currentSystem);
		else if(choice == 2) ManagerLogin(currentSystem);
		else subscriberLogin(currentSystem);
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////// Menu Helper Functions//////////////


	public static systemDataBase initializeSystem() {
		 // Create an array of administrators
		systemDataBase db = new systemDataBase();
		

       ChiefManager mainAdmin1= new ChiefManager("1", "Ross", "Geller", null, null, "system", "12345");
       ChiefManager mainAdmin2 = new ChiefManager("2", "Monica", "Geller", null, null, "admin2", "password2");
       Manager admin3 = new Manager("3", "Chandler", "Bing", null, null,"aa","aa");
       Manager admin4 = new Manager("4", "Joey", "Tribbiani", null, null,"bb","bb");
       Manager admin5 = new Manager("5", "Rachel", "Green", null, null,"cc","cc");
       db.setChiefManager(mainAdmin1);

       ArrayList<Manager> managers = new ArrayList<Manager>();
       managers.add(mainAdmin1);
       managers.add(mainAdmin2);
       managers.add(admin3);
       managers.add(admin4);
       managers.add(admin5);
       
       // Add administrators to the administrators' array
       db.setManagerList(managers);
      
      // systemDataBase ourSystem = new systemDataBase( managers);

       // Create an array of subscribers
       Subscription subscriber1 = new Subscription(1,"Phoebe", "Buffay", "Address1", "123456789","name", "name");
       Subscription subscriber2 = new Subscription(2,"Ross", "Geller", "Address2", "987654321","111","111");
       Subscription subscriber3 = new Subscription(3,"Monica", "Geller", "Address3", "111223344","222","222");
       Subscription subscriber4 = new Subscription(4,"Joey", "Tribbiani", "Address4", "555666777","333","333");
       Subscription subscriber5 = new Subscription(5,"Rachel", "Green", "Address5", "999888777","444","444");
       /*Subscription subscriber6 = new Subscription(6,"Chandler", "Bing", "Address6", "111223344");
       Subscription subscriber7 = new Subscription(7,"Janice", "Hosenstein", "Address7", "555666777");
       Subscription subscriber8 = new Subscription(8,"Gunther", "CoffeeGuy", "Address8", "999888777");
       Subscription subscriber9 = new Subscription(9,"Ursula", "Buffay", "Address9", "111223344");
       Subscription subscriber10 = new Subscription(10,"Richard", "Burke", "Address10", "555666777");*/
     
       // Add subscribers to the subscribers' ArrayList
      db.addSubscriber(subscriber1);
      db.addSubscriber(subscriber2);
      db.addSubscriber(subscriber3);
      db.addSubscriber(subscriber4);
      db.addSubscriber(subscriber5);
       /*db.addSubscriber(subscriber6);
       db.addSubscriber(subscriber7);
       db.addSubscriber(subscriber8);
       db.addSubscriber(subscriber9);
       db.addSubscriber(subscriber10);*/
       
       // Create an ArrayList of drones
       ArrayList<Drone> drones = new ArrayList<Drone>(5);
    
       boolean status;
       for (int i = 0; i < 5; i++) {
           // Example: create 10 different drones
           if (i < 2) {
           	status =(i%2==0);           
               drones.add(i, new Drone(i + 1, status, 100.0 - i * 5)); 
           }
           // Example: create 10 different express drones
           else if (i < 3) {
           	status =(i%2!=0);
           	drones.add(i, new ExpressDrone(i + 1, status, 80.0 - (i - 10) * 4, 50.0 - (i - 10) * 2, 10.0 - (i - 10)));

           }
           // Example: create 10 different distance drones
           else {
           	status =(i%2==0);
           	drones.add(i, new DistanceDrone(i + 1, status, 90.0 - (i - 20) * 4, 200.0 - (i - 20) * 10, 15.0 - (i - 20) * 0.5, 2));

           }
       }

       // Add drones to the drones' array
       
       for (Drone drone : drones) {
       	db.addDrone(drone);
       }
       db.getManagerList().get(1).addDrone(drones.get(0));//adding a drone to the system chief manager
       db.getManagerList().get(0).addDrone(drones.get(0));
       db.getManagerList().get(0).addDrone(drones.get(3));

       db.getDrone(0).addManager(mainAdmin1);
       db.getDrone(1).addManager(mainAdmin1);//use this to check add drone to manager
       db.getDrone(3).addManager(mainAdmin1);

    		 
     
       Order order = new Order(1,1,1,1,1,1,1,1);
       Order order2 = new Order(2,2,2,2,2,2,3,2);
       db.getOrders().add(order);
       db.getManagerList().get(1).addOrder(order);
       db.getManagerList().get(0).addOrder(order);
       db.getManagerList().get(0).addOrder(order2);
       db.addOrderToTable(order);
       db.addOrderToTable(order2);
       return db;
   }
	LoginForm f = new LoginForm();

	public static void displayMainMenu() {

		System.out.println("--------------------------------------------------------");
		System.out.println("Hello..");
		System.out.println("** Chief Manager page : Click 1" );
		System.out.println("** Manager page : Click 2" );
		System.out.println("** Subscription page Click 3" );
		System.out.println("--------------------------------------------------------");

	}
	
	public static void subscriberLogin(systemDataBase systemToUse) {
		Scanner scanner = new Scanner(System.in);

	    System.out.print("Enter Subscription Code: ");
	    int subCode = scanner.nextInt();

	    // Find the subscriber in the system
	    Subscription subscriber = findSubscriberByCode(systemToUse, subCode);
	    if (subscriber != null) {
	        int choice;
	        do {
	            System.out.println("Subscriber Menu:");
	            System.out.println("1. Print All Orders");
	            System.out.println("2. Update Personal Details");
	            System.out.println("3. Change Drone in Order");
	            System.out.println("4. Logout");
	            System.out.println("5. Back to menu");
	            System.out.print("Enter your choice: ");
	            choice = scanner.nextInt();
	            while(choice<0 || choice>5) {
	            	System.out.print("Invalid choice ! try again ");
		            choice = scanner.nextInt();
	            }
	            switch (choice) {
	                case 1:
	                	/*System.out.println("trying out the sub orders function that was asked to create:");
	                	System.out.println(currentSystem.subscriberOrder(subCode));//try out the return sub orders that you asked
	                	System.out.println("The normal printing function:");*/
	                    printOrdersForSubscriber(systemToUse, subscriber);
	                    break;
	                case 2:
	                    updatePersonalDetails(systemToUse, subscriber);
	                    break;
	                case 3:
	                    changeDroneInOrder(systemToUse, subscriber);
	                    break;
	                case 4:
	                    System.out.println("Logged out successfully.");
	                    break;
	                case 5:
	                	main(null);
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        } while (choice != 4);
	    } else {
	        System.out.println("Subscriber not found.");
	        System.out.println("Back to: 1.main menu , 2.subscriberLogin ");
    	    int ch = scanner.nextInt();
    	    if(ch==1) main(null);
    	    else subscriberLogin(systemToUse);
	        
	        
	    }
		
	}
	
	public static void changeDroneInOrder(systemDataBase s, Subscription subscriber) {
		 Scanner scanner = new Scanner(System.in);

		    System.out.print("Enter Order Code: ");
		    int orderCode = scanner.nextInt();

		    
		    Order order = null;
		    // Find the order for the subscriber
		    ArrayList<Manager> managers = s.getManagerList();
			for(Manager m : managers) {
				for(Order o : m.getManagerOrders()) {
					if(o.getSubCode()==subscriber.getSubCode()) {//if the subscriber was found
						order = o;
						break;
					}
				}
			}
					

		    if (order != null) {
		        System.out.println("Select Drone Type:");
		        System.out.println("1. Express Drone");
		        System.out.println("2. Distance Drone");
		        int droneType = scanner.nextInt();

		        if (droneType == 1 || droneType == 2) {
		            System.out.print("Enter Drone Code: ");
		            int newDroneCode = scanner.nextInt();
		            Drone newDrone=null;
	            	boolean status=false;
		            for(Drone d : s.getDrones()) {//find the drone to be changed
		            	if(d.getDroneCode() == newDroneCode&&d.status) {
	            			status = true;
		            		newDrone = d;
		            		break;
		            	}
		            }
		            double currentPrice , extraPrice , newPrice , extraNewPrice=0;
	            	Drone currentDrone = null;
		            if(newDrone != null && status) {
		            	//Take the current drone in the order
		            	for(Drone d : s.getDrones())
		            		if(d.getDroneCode()==order.getDroneCode())
		            		{
		            			currentDrone=d;
		            			break;
		            		}
		            	
			            //chick the type of the current drone
		            	if(currentDrone instanceof DistanceDrone)
			            	extraPrice = ((DistanceDrone)currentDrone).getExtraPrice();
		            	else if(currentDrone instanceof ExpressDrone)
		            		extraPrice = ((ExpressDrone)currentDrone).getExtraPrice();
		            	else extraPrice = 0;
		            	// here we know the old price for the current order
		            	currentPrice = order.getOrderPrice()-extraPrice;
		            	
		            	
		            	// knowing the extra price for the updating order
		            	if(droneType==1) {
		            		if(newDrone instanceof ExpressDrone)
		            		extraNewPrice = ((ExpressDrone)newDrone).getExtraPrice();
		            	  if(newDrone instanceof Drone)
		            		  extraNewPrice=0;
		            	}
		            	else {
		            		if(newDrone instanceof DistanceDrone)
		            		extraNewPrice =((DistanceDrone)newDrone).getExtraPrice();
		            		if(newDrone instanceof Drone)
		            			extraPrice = 0;
		            	}
		            	
		            	// now we calculate the new price
		            	newPrice = currentPrice + extraNewPrice ;
		            	
		            	
			            // at the end we updating the order
		            	order.setDroneCode(newDroneCode);
		            	order.setOrderPrice(newPrice);
		            	
		            	//we also change the status to unAvailble (false)
		            	for(Drone d : s.getDrones())
		            		if(d.getDroneCode()==newDroneCode)
		            		{
		            			d.setStatus(false);
		            			break;
		            		}
		            	//update the drone also in managers array
		            	for(Manager m :s.getManagerList())
		            		for(Drone d : m.getManagerDrones())
		            		{
		            			d.setStatus(false);
		            			break;
		            		}
		            	
		            	System.out.println("Drone in order changed successfully.");
		            	System.out.println("Back to: 1.main menu , 2.subscriberLogin ");
		        	    int ch = scanner.nextInt();
		        	    if(ch==1) main(null);
		        	    else subscriberLogin(s);
		          
		            } else {
		                System.out.println("Invalid drone or drone not available.");
		                System.out.println("Back to: 1.main menu , 2.subscriberLogin ");
		        	    int ch = scanner.nextInt();
		        	    if(ch==1) main(null);
		        	    else subscriberLogin(s);
		            }
		        } else {
		            System.out.println("Invalid drone type. Please try again.");
		            System.out.println("Back to: 1.main menu , 2.subscriberLogin ");
	        	    int ch = scanner.nextInt();
	        	    if(ch==1) main(null);
	        	    else subscriberLogin(s);
		        }
		    } else {
		        System.out.println("Order not found for this subscriber.");
		        System.out.println("Back to: 1.main menu , 2.subscriberLogin ");
        	    int ch = scanner.nextInt();
        	    if(ch==1) main(null);
        	    else subscriberLogin(s);
		    }
		
	}
	// Helper method to update personal details for a subscriber
	
 /***************************check this***********************************/
	public static void updatePersonalDetails(systemDataBase s, Subscription subscriber) {
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter New Address: ");
	    String newAddress = scanner.next();
	    System.out.print("Enter New Phone: ");
	    String newPhone = scanner.next();
	    subscriber.setAddress(newAddress);
	    subscriber.setPhone(newPhone);
	    Hashtable<Integer,Subscription> updateSub = s.getSubscribers();
	    //update the information also in data base 
	    for(Map.Entry<Integer,Subscription> m : updateSub.entrySet()) {
	    	if(m.getValue().getSubCode()== subscriber.getSubCode()) {
	    		m.getValue().setAddress(newAddress);
	    		m.getValue().setPhone(newPhone);
	    	}
	    }
	    System.out.println("Personal details updated successfully.");	
	}
	
	// Helper method to print all orders for a subscriber
	public static void printOrdersForSubscriber(systemDataBase s, Subscription subscriber) {
		ArrayList<Manager> managers = s.getManagerList();
		ArrayList<Order> orders = new ArrayList<Order>(0);
		int ordersCount=0;
		for(Manager m : managers) {
			for(Order o : m.getManagerOrders()) {
				if(o.getSubCode()==subscriber.getSubCode()) {
					orders.add(ordersCount, o);
					ordersCount++;
				}
			}
		}
	    if (orders.size() > 0) {
	        System.out.println("Orders for Subscriber " + subscriber.getFirstName() + " " + subscriber.getLastName() + ":");
	        for (Order order : orders) {
	            System.out.println(order.toString());
	        }
	    } else {
	        System.out.println("No orders found for this subscriber.");
	    }
	}
	
	
	public static void ManagerLogin(systemDataBase inputSystem) {
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Back to menu ? 1.Yes , 2.No , I want to add new Order");
	    int ch = scanner.nextInt();
	    if(ch==1) main(null);
	    System.out.print("Enter Manager ID: ");
	    String adminId = scanner.next();
	    
	    // Assuming you have a method to find the admin by ID
	    Manager admin = findAdminById(inputSystem, adminId);

	    if (admin != null) {
	        int subCode, droneCode, day, month, hour, minutes;
	        double price;
	        System.out.print("Enter Subscription Code: ");
	        subCode = scanner.nextInt();

	        System.out.print("Enter Drone Code: ");
	        droneCode = scanner.nextInt();

	        // Check if the subscriber and drone are in the system
	        Subscription subscriber = findSubscriberByCode(inputSystem, subCode);
	        System.out.println("after the subscriber check");
	        Drone drone = findDroneByCode(inputSystem, droneCode);
	        
	        if (subscriber == null) {
	            System.out.println("Subscription not found in the system.");
	            ManagerLogin(inputSystem);
	            return; // Added to prevent further execution
	        }

	        if (drone == null) {
	            System.out.println("Drone not found in the system.");
	            ManagerLogin(inputSystem);
	            return; // Added to prevent further execution
	        }
	        boolean checker=true;
	
	            // Check if the drone is free and the admin is responsible for it
	            if (drone.isStatus() && admin.isDroneResponsible(drone)) {
	            	System.out.print("Enter Order price: ");
	            	price = scanner.nextDouble();
	                while(price <0) {
	                	System.out.println("price should be > 0 ! Enter price again ");
	                	price = scanner.nextDouble();
	                }
	                
	                System.out.print("Enter Order Day: ");
	                day = scanner.nextInt();
	                while(day<1 || day >30) {
	                	 System.out.print("day should be between 1 and 30! Enter day again !");
	                     day = scanner.nextInt();
	                }
	                System.out.print("Enter Order Month: ");
	                month = scanner.nextInt();
	                while(month<1 || month >12) {
	                	 System.out.print("month should be between 1 and 12! Enter month again !");
	                	 month = scanner.nextInt();
	                }
	                System.out.print("Enter Order Hour: ");
	                hour = scanner.nextInt();
	                while(hour<0 || hour >23) {
	                	 System.out.print("hour should be between 0 and 23! Enter hour again !");
	                	 hour = scanner.nextInt();
	                }
	                System.out.print("Enter Order Minutes: ");
	                minutes = scanner.nextInt();
	                while(minutes<0 || minutes >59) {
	                	 System.out.print("minutes should be between 0 and 59! Enter minutes again !");
	                	 minutes = scanner.nextInt();
	                }
	                // Create an instance of Order and add it to the system
	                double orderPrice = price + calculateExtraOrderPrice(drone); 
	                
	                Order newOrder = new Order(generateOrderCode(), day, month, hour, minutes, subCode, droneCode, orderPrice);
	              //  admin.addOrder(newOrder);//adding the order to the manager class
	                inputSystem.addOrderToManager(newOrder, admin);//adding the order to the manager list in db
	                inputSystem.addOrderToTable(newOrder);//this update the order in the list and table
	                

	                // Update drone status to "busy"
	                drone.setStatus(false);

	                
	                System.out.println("Order added successfully!");
	                ManagerLogin(inputSystem);
	            } else {
	            	if(admin.isDroneResponsible(drone)==false)System.out.println("Manager not responsible for the drone.");
	            	else System.out.println("Drone not found in the system or is not available. ");
	            	ManagerLogin(inputSystem);
	            }
	      
	    } else {
	        System.out.println("Manager not found.");
	        ManagerLogin(inputSystem);
	    }
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////// ManagerLogin Helper Functions//////////////
	
	// Helper method to generate a unique order code
	public static int generateOrderCode() {
	    // You can use a combination of current timestamp and a random number for uniqueness
	    long timestamp = System.currentTimeMillis();
	    int random = (int) (Math.random() * 1000); // You can adjust the range as needed
	    return (int) (timestamp + random);
	}

	// Helper method to find an administrator by ID
	public static Manager findAdminById(systemDataBase s, String adminId) {
	    for (Manager admin : s.getManagerList()) {
	        if (admin.getId().equals(adminId)) {
	            return admin;
	        }
	    }
	    return null;
	}

	// Helper method to find a subscriber by subscription code
	public static Subscription findSubscriberByCode(systemDataBase db, int subCode) {
	    for (Map.Entry<Integer, Subscription> m : db.getSubscribers().entrySet()) {
	        if (m.getValue().getSubCode() == subCode) {
	        	System.out.println(m.getValue());
	            return m.getValue();
	        }
	    }
	    return null;
	}

	// Helper method to find a drone by drone code
	public static Drone findDroneByCode(systemDataBase s, int droneCode) {
	    for (Drone drone : s.getDrones()) {
	        if (drone.getDroneCode() == droneCode) {
               System.out.println(drone);
	            return drone;
	            
	        }
	    }
	   
	    return null;
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

	public static void ChiefManagerLogin(systemDataBase systemToUse) {
		Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Main Manager username: ");
        String username = scanner.next();

        System.out.print("Enter Main Manager password: ");
        String password = scanner.next();


        // Check if the entered credentials match the main manager
        if (username.equals("system") && password.equals("12345")) {
            System.out.println("Main Manager logged in successfully!");
            int choice;
            displayChiefManagerSubMenu();

            choice = scanner.nextInt();
            while(choice<1 || choice>5) {
            	displayChiefManagerSubMenu();
                choice = scanner.nextInt();
            }
            while(choice!=5) {
            	if(choice == 1) {         		
            		addSubscription(systemToUse);
            	}
        		else if(choice == 2) {
        			addNewManager(systemToUse);
        		}
        		else if(choice == 3) {
        			addNewDrone(systemToUse);
        		}
        		else if(choice == 4) {
        			addDroneToManager(systemToUse); 
        		}
        		else System.out.println("Invalid choice. Please enter a valid option.");
            	displayChiefManagerSubMenu();
                choice = scanner.nextInt();
            }
            if(choice == 5) {
            	System.out.println("Exit successfully!");
            	return;
            	
            }
        } 
        else {
    		System.out.println("--------------------------------------------------------");
        	System.out.println("Invalid username or password. Login failed.");
        	ChiefManagerLogin(systemToUse);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////// ChiefManagerLogin Helper Functions//////////////
	 public static void displayChiefManagerSubMenu() {
		 System.out.println("--------------------------------------------------------");
     	 System.out.println("\nChief Manager Submenu:");
         System.out.println("1. Add a Subscription");
         System.out.println("2. Add a New Manager");
         System.out.println("3. Add a New Drone");
         System.out.println("4. Add a Drone to a Manager");
         System.out.println("5. Exit");
	    }
	
	 //method to create a new subscription
	public static void addSubscription(systemDataBase s) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter Subscriber Code: ");
	    int code = scanner.nextInt();
	    System.out.print("Enter Subscriber First Name: ");
	    String firstName = scanner.next();
	    System.out.print("Enter Subscriber Last Name: ");
	    String lastName = scanner.next();
	    System.out.print("Enter Subscriber Address: ");
	    String address = scanner.next();
	    System.out.print("Enter Subscriber Phone: ");
	    String phone = scanner.next();
	    System.out.println("Enter username: ");
	    String username = scanner.next();
	    System.out.println("Enter password: ");
	    String password = scanner.next();
	    		
	    		

	    Subscription newSubscription = new Subscription(code, firstName, lastName, address, phone,username,password);
	    s.addSubscriber(newSubscription);
	  
	  
	    System.out.println("Back to: 1.main menu , 2.cheifManager menu?");
	    int ch = scanner.nextInt();
	    if(ch==1) main(null);
	    else ChiefManagerLogin(s);
	}
	
	//method to add a new Manager
	public static void addNewManager(systemDataBase s) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose the type of manager:");
        System.out.println("1. General Manager");
        System.out.println("2. Chief Manager");
        int adminType = scanner.nextInt();

        System.out.print("Enter manager ID: ");
        String id = scanner.next();
        System.out.print("Enter manager First Name: ");
        String firstName = scanner.next();
        System.out.print("Enter manager Last Name: ");
        String lastName = scanner.next();
        System.out.println("Enter manager username: ");
        String username = scanner.next();
        System.out.println("Enter manager password: ");
        String password = scanner.next();

        if (adminType == 1) {//if we want to add a general manager
            Manager newAdmin = new Manager(id, firstName, lastName, null, null,username,password);
            s.addManager(newAdmin);//adding the manager to the manager list in data base
        } else if (adminType == 2) {//if we want to add a chief manager
            System.out.print("Enter Main manager Username: ");
            String adminUsername = scanner.next();
            System.out.print("Enter Main manager Password: ");
            String adminPassword = scanner.next();
            ChiefManager newMainAdmin = new ChiefManager(id, firstName, lastName, null, null, adminUsername, adminPassword);
            s.addManager(newMainAdmin);//adding the Chief manager to the manager list in data base
        } 
        else System.out.println("Invalid choice. manager not added.");
        System.out.println("Back to: 1.main menu , 2.cheifManager menu?");
	    int ch = scanner.nextInt();
	    if(ch==1) main(null);
	    else ChiefManagerLogin(s);
    }

    //method to add a new drone  
	public static void addNewDrone(systemDataBase s) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nChoose the type of drone:");
        System.out.println("1. General Drone");
        System.out.println("2. Express Drone");
        System.out.println("3. Distance Drone");
        int droneType = scanner.nextInt();

        System.out.print("Enter Drone Code: ");
        int droneCode = scanner.nextInt();
        System.out.print("Enter Drone Status (true/false): ");
        boolean droneStatus = scanner.nextBoolean();
        System.out.print("Enter Drone Battery Level: ");
        double droneBattery = scanner.nextDouble();

        while(droneBattery<0 || droneBattery>1) {
            System.out.print("DroneBattery should be between 0 and 1! Enter droneBattery again !");
            droneBattery = scanner.nextDouble();
        }
        
        if (droneType == 1) {
            Drone newDrone = new Drone(droneCode, droneStatus, droneBattery);
            s.addDrone(newDrone);//adding the drone to the drones list in system
           //if we want to add an express drone 
        } else if (droneType == 2) {
            System.out.print("Enter Express Drone Speed: ");
            double expressSpeed = scanner.nextDouble();
            System.out.print("Enter Express Drone Extra Price: ");
            double expressExtraPrice = scanner.nextDouble();
            ExpressDrone newExpressDrone = new ExpressDrone(droneCode, droneStatus, droneBattery, expressSpeed, expressExtraPrice);
            s.addDrone(newExpressDrone);
        } else if (droneType == 3) {//if we want to add a distance drone
            System.out.print("Enter Distance Drone Max Distance: ");
            double distanceMaxDistance = scanner.nextDouble();
            while(distanceMaxDistance<0) {
                System.out.print("Max Distance should be > 0! Enter distanceMaxDistance again !");
                distanceMaxDistance = scanner.nextDouble();
            }
            
            System.out.print("Enter Distance Drone Extra Price: ");
            double distanceExtraPrice = scanner.nextDouble();
            while(distanceExtraPrice<0) {
                System.out.print("Extra price should be > 0! Enter distanceExtraPrice again !");
                distanceExtraPrice = scanner.nextDouble();
            }
            System.out.print("Enter Distance Drone Batteries Number: ");
            int batteriesNum = scanner.nextInt();
            while(batteriesNum<0) {
                System.out.print("Batteries amount should be > 0! Enter batteriesNum again !");
                batteriesNum = scanner.nextInt();
            }
            DistanceDrone newDistanceDrone = new DistanceDrone(droneCode, droneStatus, droneBattery, distanceMaxDistance, distanceExtraPrice, batteriesNum);
            s.addDrone(newDistanceDrone);          
        } 
        else System.out.println("Invalid choice. Drone not added.");
        System.out.println("Back to: 1.main menu , 2.cheifManager menu?");
	    int ch = scanner.nextInt();
	    if(ch==1) main(null);
	    else ChiefManagerLogin(s);
	}
        
     //method to add drone to manager   
	public static void addDroneToManager(systemDataBase s) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Drone Code: ");
        int droneCode = scanner.nextInt();
        System.out.print("Enter Manager ID: ");
        String managerId = scanner.next();

        Drone droneToAdd = null;
        Manager managerToAddTo = null;
        
        //Find the drone and manager in the system 
        for(Drone d : s.getDrones()) {
        	if(d.getDroneCode() == droneCode) {
        		droneToAdd = d;
        		break;
        	}
        }
        if(droneToAdd==null) {
        	System.out.println("Invalid Manager ID.");
        	addDroneToManager(s);
        	return;
        }
        for(Manager m : s.getManagerList()) {
        	if(m.getId().equals(managerId)) {
        		managerToAddTo = m;
        		break;
        	
        	}
        }
        
         if(managerToAddTo==null) {
        	System.out.println("Invalid Drone Code.");
        	addDroneToManager(s);
        }
         
        //updating the data base
        s.addDroneToManager(droneToAdd);//adding the drone to the manager
        s.addManagerToDrone(managerToAddTo);//adding the manager to the drone
        
        System.out.println("Back to: 1.main menu , 2.cheifManager menu?");
	    int ch = scanner.nextInt();
	    if(ch==1) main(null);
	    else ChiefManagerLogin(s);
	}


	

}
