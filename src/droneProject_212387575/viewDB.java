package droneProject_212387575;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class viewDB {
	protected JFrame log, viewDB;
	protected systemDataBase db;
	protected ArrayList<ChiefManager> mainManagers = new ArrayList<ChiefManager>();
	protected JButton prev;
	
	public viewDB(JFrame log, systemDataBase db) {
		this.log = log;
		this.db = db;
		viewDB = new JFrame("view Data Base");
		viewDB.setVisible(true);
		viewDB.setSize(535,512);
		viewDB.getContentPane().setLayout(null);
		
		File file = new File("src/droneProject_212387575/AllManager.txt");
		if (!file.exists()) {
    	    System.out.println("File not found: " + file.getAbsolutePath());
    	    return; // Exit the method if the file is not found
    	}
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
    	    String line;
    	    ChiefManager newManager;    	   
    	    while ((line = bufferedReader.readLine())!=null) {
    	    	int i=0;
    	        String[] parts = line.split(" "); //split by whitespace
    	        if (parts.length == 7) {      	        	
    	            String id = parts[0];
    	            System.out.println(id);
    	            String firstName = parts[1];
    	            System.out.println(firstName);
    	            String lastName = parts[2];
    	            System.out.println(lastName);
    	            String username = parts[5];
    	            System.out.println(username);
    	            String password = parts[6];
    	            System.out.println(password);
    	            // Create a new Manager object
    	            newManager = new ChiefManager(id, firstName, lastName, null, null, username, password);
    	            mainManagers.add(newManager);
    	            
    	           
    	        }
    	        //if there are orders and or drones
    	        if(parts.length > 7) {
    	        	
    	        	//if he has a list of drones and sub
    	        	
    	        	 ArrayList<Drone> drones = new ArrayList<Drone>();
   	        		ArrayList<Order> orders = new ArrayList<Order>();
    	        	while(i < parts.length-1) {
    	        		
    	        	 String id = parts[i];   	        	 
        			 i++;
      	            String firstName = parts[i];      	            
       			     i++;
      	            String lastName = parts[i];
      	            i++;
      	         
    	        	if(parts[i].equals(",")) {//if there are drones
    	        	i++;
    	        		while((!parts[i].equals(":")) && (!parts[i].equals("null"))) {  
    	        			// System.out.println("Current part: " + parts[i]+"is this it");
    	        	      
    	        			//check the type of drone
              	            if(parts[i].equals("DistanceDrone")) {
              	            	i++;
              	            	int DroneCode = Integer.parseInt(parts[i]);
              	            	i++;
								boolean status = Boolean.valueOf(parts[i]);
								i++;
								double battery = Double.parseDouble(parts[i]);
								i++;
								double maxDistance = Double.parseDouble(parts[i]);
								i++;
								double extraPrice = Double.parseDouble(parts[i]);
								i++;
								int batteriesNum = Integer.parseInt(parts[i]);
								i++;
								Drone drone = new DistanceDrone(DroneCode,status,battery,maxDistance,extraPrice,batteriesNum);
								drones.add(drone);
              	            }else if(parts[i].equals("ExpressDrone")) {
              	            	i++;
              	            	int DroneCode = Integer.parseInt(parts[i]);
              	            	i++;
								boolean status = Boolean.valueOf(parts[i]);
								i++;
								double battery = Double.parseDouble(parts[i]);
								i++;
								double speed = Double.parseDouble(parts[i]);
								i++;
								double extraPrice = Double.parseDouble(parts[i]);
								i++;
								Drone drone = new ExpressDrone(DroneCode, status,battery,speed,extraPrice);
								drones.add(drone);
              	            }else if(parts[i].equals("Drone")) {
              	            	i++;
              	            	int DroneCode = Integer.parseInt(parts[i]);
              	            	
              	            	i++;
								boolean status = Boolean.valueOf(parts[i]);
								
								i++;
								double battery = Double.parseDouble(parts[i]);
								
								i++;
								Drone drone = new Drone(DroneCode,status, battery);
								drones.add(drone);
								
								
              	            }else {
              	            	JOptionPane.showMessageDialog(null, "Error reading drones", "Invalid drones format",
                  	   			        JOptionPane.ERROR_MESSAGE);
                  	  			return;
              	            }
              	           
    	        		}
    	        		if(parts[i].equals("null")) {//if there are no orders
    	        			i++;
    	        			String username = parts[i];
              	            i++;
              	            String password = parts[i];
              	            i++;
              	          if(orders.isEmpty() && !drones.isEmpty()) {
              	        	ChiefManager m = new ChiefManager(id, firstName,lastName,drones,null,username,password);
            	        		mainManagers.add(m);
            	        		
            	        	}
    	        		}else if(parts[i].equals(":")) {
    	        			i++;
    	        			while(!(parts[i].equals(";"))) {
    	        				int code = Integer.parseInt(parts[i]);
    	        				i++;
    	        				int day = Integer.parseInt(parts[i]);
    	        				i++;
    	        				int month = Integer.parseInt(parts[i]);
    	        				i++;
    	        				int hour = Integer.parseInt(parts[i]);
    	        				i++;
    	        				int minute = Integer.parseInt(parts[i]);
    	        				i++;
    	        				int subCode = Integer.parseInt(parts[i]);
    	        				i++;
    	        				int droneCode = Integer.parseInt(parts[i]);
    	        				i++;
    	        				double orderPrice = Double.parseDouble(parts[i]);
    	        				i++;
    	        				Order order = new Order(code,day,month,hour,minute,subCode,droneCode,orderPrice);
    	        				orders.add(order);
    	        			}
    	        			i++;
    	        			String username = parts[i];
              	            i++;
              	            String password = parts[i];
              	            i++;
              	            
              	          if(!orders.isEmpty() && !drones.isEmpty()) {
              	        	ChiefManager m = new ChiefManager(id, firstName,lastName,drones,orders,username,password);
          	        		mainManagers.add(m);
          	        		//System.out.println(m);
          	        		
          	        	}
    	        		}
    	        		else {
    	        			JOptionPane.showMessageDialog(null, "Error reading orders", "Incorrect Order format",
    	         			        JOptionPane.ERROR_MESSAGE);
    	        			return;
    	        		}
    	        	}else {
    	        		if(parts[i].equals("null")) {//if there are no drones                	        			
    	        			i++;
    	        			if(parts[i].equals("null")) {//if there are no orders
    	        				i++;
    	        		String username = parts[i];
          	            i++;
          	            String password = parts[i];
          	            i++;
          	          if(orders.isEmpty() && drones.isEmpty()) {
          	        	ChiefManager m = new ChiefManager(id, firstName,lastName,null,null,username,password);
      	        		mainManagers.add(m);
      	        	}
          	           
    	        			}else {//there are orders
    	        				//create an order and add to orders until we reach ";"
    	        				i++;
    	        				while(!parts[i].equals(";")) {
        	        				int code = Integer.parseInt(parts[i]);
        	        				i++;
        	        				int day = Integer.parseInt(parts[i]);
        	        				i++;
        	        				int month = Integer.parseInt(parts[i]);
        	        				i++;
        	        				int hour = Integer.parseInt(parts[i]);
        	        				i++;
        	        				int minute = Integer.parseInt(parts[i]);
        	        				i++;
        	        				int subCode = Integer.parseInt(parts[i]);
        	        				i++;
        	        				int droneCode = Integer.parseInt(parts[i]);
        	        				i++;
        	        				double orderPrice = Double.parseDouble(parts[i]);
        	        				i++;
        	        				Order order = new Order(code,day,month,hour,minute,subCode,droneCode,orderPrice);
        	        				orders.add(order);
    	        			}
    	        				i++;
    	        				String username = parts[i];
                  	            i++;
                  	            String password = parts[i];
                  	            i++;
                  	          if(!orders.isEmpty() && drones.isEmpty()) {
                  	        	ChiefManager m = new ChiefManager(id, firstName,lastName,null,orders,username,password);
              	        		mainManagers.add(m);
              	        		
              	        	}
    	        			
    	        		}
    	        		}
    	        		else {
    	        			JOptionPane.showMessageDialog(null, "Error reading file.", "Invalid file",
    	         			        JOptionPane.ERROR_MESSAGE);
    	        			return;
    	        		}
    	        	}
    	        	}
    	        }
    	        
    	        
    	        if(parts.length<7) {               	        			
    	        	JOptionPane.showMessageDialog(null, "The file is not correct.", "Invalid file info",
	        			        JOptionPane.ERROR_MESSAGE);
    				return;
    	        }
    	    }
    	    boolean found = true;
    	    //check for duplicate id
    	    for(int i=0; i<mainManagers.size();i++){
    	    	for(int j=i+1 ;j<mainManagers.size();j++) {
    	    		if(mainManagers.get(i).getId().equals(mainManagers.get(j).getId())) {
    	    			System.out.println(mainManagers.get(i).getId());
    	    			System.out.println(mainManagers.get(j).getId());
    	    			found = false;
    	    			JOptionPane.showMessageDialog(null, "There are two or more managers with the same ID", "Error",
 	        			        JOptionPane.ERROR_MESSAGE);
        				return;
    	    		}
    	    	}
    	    	
    	    }
	        bufferedReader.close();
    	} catch (FileNotFoundException ex) {
    	    System.out.println("File not found: " + file.getAbsolutePath());
    	    ex.printStackTrace();
    	} catch (IOException ex) {
    	    System.out.println("An error occurred while reading the file.");
    	    ex.printStackTrace();
    	}
		int len = mainManagers.size();
		//sort them in the table
		String[] col = {"id", "First Name", "Last Name", "Drones", "Orders", "Username", "Password"};
		String[][] data = new String[len][7];		
		int i=0;
		int k=0;
			
			for(ChiefManager m :mainManagers) {
				data[k][0]= m.getId().toString();
				System.out.println(m.getId());
				data[k][1] = m.getFirstName();
				data[k][2] = m.getLastName();
				if(m.getManagerDrones().isEmpty()) {//check if there are drones to add
					data[k][3] = "no drones";
				}else {
					for(i=0;i<m.getManagerDrones().size();i++) {
						data[k][3] = m.getManagerDrones().get(i).toString()+", ";
						
					}
				}
				if(m.getManagerOrders().isEmpty()) {//check if there are orders
					data[k][4] = "no orders";
				}else {
					for(i=0;i<m.getManagerOrders().size();i++) {
						data[k][4] = m.getManagerOrders().get(i).toString()+", ";
						
					}
				}
				data[k][5] = m.getUserName();
				data[k][6] = m.getPassword();
				k++;
			}
				
		
		JTable jtable=new JTable(data,col);
		jtable.setVisible(true);
		JScrollPane sp=new JScrollPane(jtable);
		sp.setLocation(0, 0);
		 sp.setBounds(10, 10, 480, 450); 
		 viewDB.getContentPane().add(sp);
		
		
	}

}
