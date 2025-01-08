package droneProject_212387575;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class cheifManagerFrame {
    protected JFrame loginFrame;
    protected JPanel panel;
    protected JButton showSub, showManager, showDrone, addManager, addDTM, addDrone, calculator,CollectManagers, CollectSub;
    protected JMenu menu;
    protected JMenuItem downloadManager, downloadSubscription, downloadDrone, downloadOrder;
    protected JMenuBar bar;

    public cheifManagerFrame(JFrame loginFrame, systemDataBase db) {
        JFrame cml = new JFrame("Chief Manager log in");
        this.loginFrame = loginFrame;
        loginFrame.setVisible(false); // Hide the login frame
        panel = new JPanel();
        panel.setLayout(null);
        
        bar = new JMenuBar();
        menu = new JMenu("Download");
        downloadManager = new JMenuItem("Download Manager");
        downloadSubscription = new JMenuItem("Download Subrscribers");
        downloadDrone = new JMenuItem("Download Drones");
        downloadOrder = new JMenuItem("Download Orders");
        menu.add(downloadManager);
        menu.add(downloadSubscription);
        menu.add(downloadDrone);
        menu.add(downloadOrder);
        bar.add(menu);
        downloadDrone.addActionListener(new ActionListener() {//download the drones to drone.txt
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== downloadDrone) {//sorting them based on the drones code
                	Comparator<Drone> compareFunc = (m1, m2) -> {
                        int code1 = m1.getDroneCode();
                        int code2 = m2.getDroneCode();
                        return code1- code2;         
                        };
                   ArrayList<Drone> drones = new ArrayList<Drone>();//collecting the drones from that data base
                   for(Drone o :systemDataBase.drones) {
                	   drones.add(o);
                   }
                    Collections.sort(drones, compareFunc);//sort them by the subscriber last name
                    File file = new File("src/drone.txt");//access the file we are writing to
                    BufferedWriter out;
					try {//check if we can write to the file
						out = new BufferedWriter(new FileWriter(file));
						for(Drone m: drones) {
							out.write(m.getDroneCode() + " ");
							out.write(m.isStatus() + " ");
							out.write(m.getBattery() + " ");
							//check what is the kind of drone
							if(m instanceof ExpressDrone) {
								out.write(((ExpressDrone)m).getSpeed()+ " ");
								out.write(((ExpressDrone)m).getExtraPrice()+ " ");
							}else
							if(m instanceof DistanceDrone) {
								out.write(((DistanceDrone)m).getMaxDistance()+ " ");
								out.write(((DistanceDrone)m).getExtraPrice()+ " ");
								out.write(((DistanceDrone)m).getBatteriesNum()+ " ");
							}
							out.write("\n");
							ArrayList<Manager> managers = m.managers;
							for(Manager o: managers) {
								out.write(o.getId()+" ");
								out.write(o.getFirstName()+" ");
								out.write(o.getLastName()+ "");
							}
							out.write("\n");
						}
					out.close();
					JOptionPane.showMessageDialog(null, "Drones Downloaded to file", "Success",
	        			        JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {//if the file is not found
						JOptionPane.showMessageDialog(null, "File not found", "Error",
 	        			        JOptionPane.ERROR_MESSAGE);
        				e1.printStackTrace();
        				return;	
					}
                }
            }
        });
        //download the orders to orders.txt
        downloadOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== downloadOrder) {
                	//sorting them by order code
                	Comparator<Order> compareFunc = (m1, m2) -> {
                        int code1 = m1.getOrderCode();
                        int code2 = m2.getOrderCode();
                        return code1- code2;         
                        };
                        //get the orders from the data base
                   ArrayList<Order> orders = new ArrayList<Order>();
                   for(Order o :systemDataBase.orders) {
                	   orders.add(o);
                   }
                    Collections.sort(orders, compareFunc);//sort them by the subscriber last name
                    File file = new File("src/orders.txt");//access the file we are writing to
                    BufferedWriter out;
					try {//check if we can write to the file
						out = new BufferedWriter(new FileWriter(file));
						for(Order m: orders) {
							out.write(m.getOrderCode() + " ");
							out.write(m.getDay() + " ");
							out.write(m.getMonth() + " ");
							out.write(m.getHour()+ " ");
							out.write(m.getMinutes()+ " ");
							out.write(m.getSubCode()+ " ");
							out.write(m.getDroneCode()+ " ");
							out.write(m.getOrderPrice()+ " ");
							out.write("\n");
						}
					out.close();
					JOptionPane.showMessageDialog(null, "Orders Downloaded to file", "Success",
	        			        JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {//if there was an error writing to the file
						JOptionPane.showMessageDialog(null, "File not found", "Error",
 	        			        JOptionPane.ERROR_MESSAGE);
        				e1.printStackTrace();
        				return;	
					}
                }
            }
        });
        //download the subscribers to members.txt
        downloadSubscription.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== downloadSubscription) {
                	//sort them based on their last name
                	Comparator<Subscription> compareFunc = (m1, m2) -> {
                        String code1 = m1.getLastName();
                        String code2 = m2.getLastName();
                        return code1.compareTo(code2);                    };
                    ArrayList<Subscription> SubscriptionDownload = new ArrayList<Subscription>();
                    //collect them from the data base
                    for(Map.Entry<Integer, Subscription> m : db.getSubscribers().entrySet()) {
                    	SubscriptionDownload.add(m.getValue());
                    }
                    Collections.sort(SubscriptionDownload, compareFunc);//sort them by their last name
                    File file = new File("src/members.txt");//access the file we are writing to
                    BufferedWriter out;
					try {//check if we can write to the file
						out = new BufferedWriter(new FileWriter(file));
						for(Subscription m: SubscriptionDownload) {
							out.write(m.getSubCode() + " ");
							out.write(m.getFirstName() + " ");
							out.write(m.getLastName() + " ");
							out.write(m.getAddress()+ " ");
							out.write(m.getPhone()+ " ");
							out.write(m.getUsername()+ " ");
							out.write(m.getPassword()+ " ");
							out.write("\n");
						}
					out.close();
					JOptionPane.showMessageDialog(null, "Subscribers Downloaded to file", "Success",
	        			        JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {//if there was an error
						JOptionPane.showMessageDialog(null, "File not found", "Error",
 	        			        JOptionPane.ERROR_MESSAGE);
        				e1.printStackTrace();
        				return;	
					}
                }
            }
        });
        //download the managers to SystemManagers.txt
        downloadManager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== downloadManager) {
                	//sorting them based on their id
                	Comparator<Manager> compareFunc = (m1, m2) -> {
                        int id1 = Integer.parseInt(m1.getId());
                        int id2 = Integer.parseInt(m2.getId());
                        return id1 - id2;
                    };
                    ArrayList<Manager> managerDownload = new ArrayList<Manager>();
                    for(Manager m:db.getManagerList()) {//collect only the general managers
                    	if(!(m instanceof ChiefManager)) {
                    		managerDownload.add(m);
                    	}
                    }
                    Collections.sort(managerDownload, compareFunc);//sort them by their id
                    File file = new File("src/droneProject_212387575/SystemManagers.txt");//access the file we are writing to
                    BufferedWriter out;
					try {//check if we can write to the file
						out = new BufferedWriter(new FileWriter(file));
						for(Manager m: managerDownload) {
							out.write(m.getId() + " ");
							out.write(m.getFirstName() + " ");
							out.write(m.getLastName() + " ");
							if(!m.getManagerDrones().isEmpty()) {//check if the manager is responsible for a drone
								out.write(", ");
								for(Drone d: m.getManagerDrones()) {
									//check for drone type
									if(d instanceof DistanceDrone) {
										out.write("DistanceDrone ");//write the type of drone so we know when we read the file
										out.write(d.getDroneCode()+ " ");
										String s = String.valueOf(d.isStatus());
										out.write(s+ " ");
										String battery = String.valueOf(d.getBattery());
										out.write(battery+ " ");
										String i = String.valueOf(((DistanceDrone)d).getMaxDistance());
										out.write(i+ " ");
										String i2 = String.valueOf(((DistanceDrone)d).getExtraPrice());
										out.write(i2+ " ");
										out.write(((DistanceDrone)d).getBatteriesNum()+ " ");									
									}
									else if(d instanceof ExpressDrone) {
										out.write("ExpressDrone ");
										out.write(d.getDroneCode()+ " ");
										String s = String.valueOf(d.isStatus());
										out.write(s+ " ");
										String battery = String.valueOf(d.getBattery());
										out.write(battery+ " ");
										String speed = String.valueOf(((ExpressDrone)d).getSpeed());
										out.write(speed+ " ");
										String price = String.valueOf(((ExpressDrone)d).getExtraPrice());
										out.write(price+ " ");								
									}
									else {
										out.write("Drone ");
										out.write(d.getDroneCode()+ " ");
										String s = String.valueOf(d.isStatus());
										out.write(s+ " ");
										String battery = String.valueOf(d.getBattery());
										out.write(battery+ " ");
									}
								}
								
							}else {
								out.write("null ");
							}
							//check if the manager has any orders
							if(!m.getManagerOrders().isEmpty()) {
								out.write(": ");//to know when we read the file that it is an order
								for(Order entry : m.managerOrders) {
									out.write(entry.getOrderCode()+ " ");
									out.write(entry.getDay()+ " ");
									out.write(entry.getMonth()+ " ");
									out.write(entry.getHour()+ " ");
									out.write(entry.getMinutes()+ " ");
									out.write(entry.getSubCode()+ " ");
									out.write(entry.getDroneCode()+ " ");
									String price = String.valueOf(entry.getOrderPrice());
									out.write(price+ " ");					
								}
								out.write("; ");
								
							}else {
								out.write("null ");
							}
							out.write(m.getUsername()+ " ");
							out.write(m.getPassword()+ " ");
							out.write("\n");
						}
					out.close();
					JOptionPane.showMessageDialog(null, "Managers Downloaded to file", "Success",
	        			        JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "File not found", "Error",
 	        			        JOptionPane.ERROR_MESSAGE);
        				e1.printStackTrace();
        				return;	
					}
                }
            }
        });
        
        CollectSub  = new JButton("Collect Subscribers");
        CollectSub.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        CollectSub.setBackground(new Color(100, 149, 237));
        CollectManagers = new JButton("Collect Managers");
        CollectManagers.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        CollectManagers.setBackground(new Color(100, 149, 237));
        calculator = new JButton("Calculator");
        calculator.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        calculator.setBackground(new Color(100, 149, 237));
        showSub = new JButton("View Subscribers");
        showSub.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        showSub.setBackground(new Color(100, 149, 237));
        showManager = new JButton("View Managers");
        showManager.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        showManager.setBackground(new Color(100, 149, 237));
        showDrone = new JButton("View Drones");
        showDrone.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        showDrone.setBackground(new Color(100, 149, 237));
        addManager = new JButton("Add Manager");
        addManager.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        addManager.setBackground(new Color(100, 149, 237));
        addDTM = new JButton("Add Drone To Manager");
        addDTM.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        addDTM.setBackground(new Color(100, 149, 237));
        addDrone = new JButton("Add Drone");
        addDrone.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        addDrone.setBackground(new Color(100, 149, 237));
        
        //collect the managers from the file SystemManagers
        CollectManagers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == CollectManagers) {
                	File file = new File("src/droneProject_212387575/SystemManagers.txt");
                	if (!file.exists()) {
                	    System.out.println("File not found: " + file.getAbsolutePath());
                	    return; // Exit the method if the file is not found
                	}
                	try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                	    String line;
                	    Manager newManager;
                	    ArrayList<Manager> managers = new ArrayList<Manager>();
                	    while ((line = bufferedReader.readLine())!=null) {
                	    	int i=0;
                	        String[] parts = line.split(" "); //split by whitespace
                	        if (parts.length == 7) { //if there are no orders or drones     	        	
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
                	            newManager = new Manager(id, firstName, lastName, null, null, username, password);
                	            managers.add(newManager);
                	            
                	           
                	        }
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
                  	         
                	        	if(parts[i].equals(",")) {//if we arrived at a drone read
                	        	i++;
                	        		while((!parts[i].equals(":")) && (!parts[i].equals("null"))) { //until we get to the orders 
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
                          	            }else {//if there is an error in how the drones were written
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
                          	          if(orders.isEmpty() && !drones.isEmpty()) {//create a manager to add
                        	        		Manager m = new Manager(id, firstName,lastName,drones,null,username,password);
                        	        		managers.add(m);                        	        		
                        	        	}
                	        		}else if(parts[i].equals(":")) {//if there are orders
                	        			i++;
                	        			while(!(parts[i].equals(";"))) {//while we didn't get to the end of the orders
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
                          	            
                          	          if(!orders.isEmpty() && !drones.isEmpty()) {//create a new manager to add
                      	        		Manager m = new Manager(id, firstName,lastName,drones,orders,username,password);
                      	        		managers.add(m);
                      	        		
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
                  	        		Manager m = new Manager(id, firstName,lastName,null,null,username,password);
                  	        		managers.add(m);
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
                          	        		Manager m = new Manager(id, firstName,lastName,null,orders,username,password);
                          	        		managers.add(m);
                          	        		
                          	        	}               	        			
                	        		}
                	        		}
                	        		else {//if there was an error reading the file
                	        			JOptionPane.showMessageDialog(null, "Error reading file.", "Invalid file",
                	         			        JOptionPane.ERROR_MESSAGE);
                	        			return;
                	        		}
                	        	}
                	        	}
                	        }                	        
                	        if(parts.length<7) {    //if how the file was written is not correct           	        			
                	        	JOptionPane.showMessageDialog(null, "The file is not correct.", "Invalid file info",
         	        			        JOptionPane.ERROR_MESSAGE);
                				return;
                	        }
                	    }
                	    boolean found = true;
                	    //check that there arn't two managers with the same id
                	    for(int i=0; i<managers.size();i++){
                	    	for(int j=i+1 ;j<managers.size();j++) {
                	    		if(managers.get(i).getId().equals(managers.get(j).getId())) {
                	    			System.out.println(managers.get(i).getId());
                	    			System.out.println(managers.get(j).getId());
                	    			found = false;
                	    			JOptionPane.showMessageDialog(null, "There are two or more managers with the same ID", "Error",
             	        			        JOptionPane.ERROR_MESSAGE);
                    				return;
                	    		}
                	    	}
                	    	
                	    }
                	    if(found) {//if they didn't find duplicate id
                	    	for(Manager s : managers) {
                	    		db.addManager(s);
                	    		System.out.println(s);
                	    	}
                	    	System.out.println("Manager added successfully from the file.");
            	            JOptionPane.showMessageDialog(null, "Manager added successfully from the file.", "Subscriber added",
     	        			        JOptionPane.INFORMATION_MESSAGE);
            				return;
                	    }
            	        bufferedReader.close();
                	} catch (FileNotFoundException ex) {//if there wan an error reading the file
                	    System.out.println("File not found: " + file.getAbsolutePath());
                	    ex.printStackTrace();
                	} catch (IOException ex) {
                	    System.out.println("An error occurred while reading the file.");
                	    ex.printStackTrace();
                	}

                }
            }
        });

        CollectSub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == CollectSub) {
                	File file = new File("src/members.txt");
                	if (!file.exists()) {
                	    System.out.println("File not found: " + file.getAbsolutePath());
                	    return; // Exit the method if the file is not found
                	}
                	try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                	    String line;
                	    Subscription sub;
        	            ArrayList<Subscription> allSub = new ArrayList<Subscription>();
                	    while ((line = bufferedReader.readLine()) != null) {
                	        String[] parts = line.split(" "); //split by whitespace
                	        System.out.println("Parts length: " + parts.length); // debug statement
                	        if (parts.length == 7) {
                	            String code = parts[0];
                	            int codeInt = Integer.parseInt(code);
                	            String firstName = parts[1];
                	            String lastName = parts[2];
                	            String address = parts[3];
                	            String phone = parts[4];
                	            String username = parts[5];
                	            String password = parts[6];
                	            // Create a new Manager object
                	            sub = new Subscription(codeInt ,firstName , lastName, address , phone , username , password);
                	            allSub.add(sub);
                	        }
                	        
                	        if(parts.length<7 || parts.length>7) {
                	        	JOptionPane.showMessageDialog(null, "The file is not correct.", "Invalid file info",
         	        			        JOptionPane.ERROR_MESSAGE);
                				return;
                	        }                	        
                	        
                	    }
                	    boolean found = true;
                	    for(int i=0; i<allSub.size();i++){
                	    	for(int j=i+1 ;j<allSub.size();j++) {
                	    		if(allSub.get(i).getSubCode() == allSub.get(j).getSubCode()) {
                	    			found = false;
                	    			JOptionPane.showMessageDialog(null, "There are two or more Sub with the same code", "Error",
             	        			        JOptionPane.ERROR_MESSAGE);
                    				return;
                	    		}
                	    	}
                	    	
                	    }
                	    if(found) {
                	    	for(Subscription s : allSub) {
                	    		db.addSubscriber(s);
                	    	}
                	    	System.out.println("Subscriber added successfully from the file.");
            	            JOptionPane.showMessageDialog(null, "Subscriber added to data base.", "Subscriber added",
     	        			        JOptionPane.INFORMATION_MESSAGE);
            				return;
                	    }
            	        bufferedReader.close();
                	} catch (FileNotFoundException ex) {
                	    System.out.println("File not found: " + file.getAbsolutePath());
                	    ex.printStackTrace();
                	} catch (IOException ex) {
                	    System.out.println("An error occurred while reading the file.");
                	    ex.printStackTrace();
                	}

                }
            }
        });

        
        calculator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== calculator) {
                    cml.setVisible(true);
                    new Calculator(cml,db);
                }
            }
        });
        
        showSub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== showSub) {
                    cml.setVisible(true);
                    new ShowSub(cml,db);
                }
            }
        });
        
        showManager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == showManager) {
                    cml.setVisible(true);
                    new ShowManager(cml,db);
                }
            }
        });
        
        showDrone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == showDrone) {
                    cml.setVisible(true);
                    new ShowDrone(cml,db);
                }
            }
        });
        
        addManager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addManager) {
                    cml.setVisible(true);
                    new addManager(cml,db);
                }
            }
        });
        
        addDrone.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addDrone) {
                    cml.setVisible(true);
                    new AddDrone(cml,db);
                }
            }
        });
        
        addDTM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addDTM) {
                    cml.setVisible(true);
                    new DroneToManager(cml,db);
                }
            }
        });
        
        CollectSub.setBounds(0, 400, 486, 53);
        CollectManagers.setBounds(0, 352, 486, 53);
        calculator.setBounds(0, 0, 486, 53);
        showSub.setBounds(0, 302, 486, 53);
        showManager.setBounds(0, 250, 486, 53);
        showDrone.setBounds(0, 200, 486, 53);
        addManager.setBounds(0, 50, 486, 53);
        addDrone.setBounds(0, 99, 486, 53);
        addDTM.setBounds(0, 151, 486, 53);

        panel.add(CollectSub);
        panel.add(CollectManagers);
        panel.add(calculator);
        panel.add(showSub);
        panel.add(showManager);
        panel.add(showDrone);
        panel.add(addManager);
        panel.add(addDrone);
        panel.add(addDTM);
        
        cml.setJMenuBar(bar);
        cml.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cml.setSize(500, 522);
        cml.setContentPane(panel);
        cml.getContentPane().setLayout(null);
        cml.setVisible(true);
    }
}
