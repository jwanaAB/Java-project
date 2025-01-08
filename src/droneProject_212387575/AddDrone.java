package droneProject_212387575;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddDrone implements ItemListener {
    protected JFrame cmf, ad;
    protected systemDataBase db;
    protected Choice DroneType = new Choice();
    protected JLabel codel, statusl, batteryl, maxDistancel, extraPricel, speedl, batteriesNuml, dronet;
    protected JTextField code, status, battery, maxDistance, extraPrice, speed, batteriesNum;
    protected JButton addM,prev;

    public AddDrone(JFrame cmf, systemDataBase db) {
        this.db = db;//link the data base and the previous page
        this.cmf = cmf;
        DroneType.add("1.General Drone");
        DroneType.add("2.Express Drone");
        DroneType.add("3.Distance Drone");

        ad = new JFrame("Add Drone");
        codel = new JLabel("Code: ");
        statusl = new JLabel("Status: ");
        batteryl = new JLabel("Battery percentage:");
        maxDistancel = new JLabel("Max Distance : ");
        extraPricel = new JLabel("Extra Price: ");
        speedl = new JLabel("Drone Speed: ");
        dronet = new JLabel("Enter Drone Type:");
        batteriesNuml = new JLabel("Battery Number:");

        code = new JTextField(20);
        status = new JTextField(20);
        battery = new JTextField(20);
        maxDistance = new JTextField(20);
        extraPrice = new JTextField(20);
        speed = new JTextField(20);
        batteriesNum = new JTextField(20);

        addM = new JButton("Add Drone To System");
        addM.setFont(new Font("Times New Roman", Font.BOLD, 15));

        codel.setBounds(40, 90, 84, 20);
        code.setBounds(207, 91, 233, 20);
        statusl.setBounds(40, 128, 106, 20);
        status.setBounds(207, 129, 233, 20);
        batteryl.setBounds(40, 168, 129, 20);
        battery.setBounds(207, 169, 233, 20);
        dronet.setBounds(40, 50, 106, 20);
        DroneType.setBounds(207, 52, 233, 18);

        speedl.setBounds(40, 199, 100, 20);
        speed.setBounds(207, 200, 233, 20);
        extraPricel.setBounds(40, 229, 100, 20);
        extraPrice.setBounds(207, 230, 233, 20);
        maxDistancel.setBounds(40, 259, 100, 20);
        maxDistance.setBounds(207, 258, 233, 20);
        batteriesNuml.setBounds(40, 287, 150, 20);
        batteriesNum.setBounds(207, 288, 233, 20);
        addM.setBounds(250, 340, 200, 30);
        
       
        // Adding ItemListener to DroneType
        DroneType.addItemListener(this);
      
        addM.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 String code1 = code.getText();
        	      
        	        String status1 = status.getText();
        	        String battery1 = battery.getText();
        	        String speed1 = speed.getText();
        	        String extraPrice1 = extraPrice.getText();
        	        String maxDistance1 = maxDistance.getText();
        	        String batteriesNum1 = batteriesNum.getText();
        		  boolean b=true;
        		if(e.getSource() == addM) {//check if the status input was valid
        			if(status1.equals("true")|| status1.equals("True")) {
        	        	b = true;
        	        }else if(status1.equals("false") || status1.equals("False")) {
        	        	 b = false;
        	        }else {
        	        	 JOptionPane.showMessageDialog(null, "Invalid status value. Please enter 'true' or 'false'.", "Invalid status",
 	        			        JOptionPane.ERROR_MESSAGE);
        	        	 return;

        	        }
        			try {//check if the code value is valid
        				 int codeValue = Integer.parseInt(code1);
        			}catch (NumberFormatException ex){
        				JOptionPane.showMessageDialog(null, "Invalid code. Please enter numeric numbers.", "Invalid code",
 	        			        JOptionPane.ERROR_MESSAGE);
        				return;
        			}
        			try {//check if the battery value is valid
        				Double batteryValue = Double.parseDouble(battery1);
        				if(batteryValue>1 || batteryValue<0) {
        					 throw new NumberFormatException();        				}
        			}catch(NumberFormatException ex){
        				JOptionPane.showMessageDialog(null, "DroneBattery should be between 0 and 1!", "Invalid DroneBattery",
 	        			        JOptionPane.ERROR_MESSAGE);
        				return;
        			}
        	        String selectedDroneType = DroneType.getSelectedItem();//get the selected drone type
                      
        	        
        	       if(selectedDroneType.equals("1.General Drone")) {//if it's a general drone

        	        Drone d = new Drone(Integer.parseInt(code1),b,Double.parseDouble(battery1));//create a drone to add
        	        
        	         boolean found = false;
        	        for(int i=0;i<systemDataBase.drones.size();i++) {//check if the drone has been already added
        	        	if(systemDataBase.drones.get(i).getDroneCode() == Integer.parseInt(code1)){
        	        		 JOptionPane.showMessageDialog(null, "Drone already exists!", "drone exists",
        	        			        JOptionPane.INFORMATION_MESSAGE);
        	        		 found = true;
        	        	}
        	        }
        	        if(found == false) {//this means that the drone was not found
            	        db.addDrone(d);
        	        JOptionPane.showMessageDialog(null, "Drone added successfully!", "Success",
        	        JOptionPane.INFORMATION_MESSAGE);
        	        ad.dispose();
        	        }

        	        }
        	       try {//check if the extra price value is valid
           	        Double extraPriceV = Double.parseDouble(extraPrice1);
           	        if(extraPriceV<0) {
           	         throw new NumberFormatException(); 
           	        }
           	        }catch(NumberFormatException ex){
           	        	JOptionPane.showMessageDialog(null, "Invalid Extra Price. Please enter numeric value larger than 0.", "Invalid Extra Price",
    	        			        JOptionPane.ERROR_MESSAGE);
           	        	 return;
           	        }
        	       
        	      //if the selected drone is distance drone 
        	       if(selectedDroneType.equals("3.Distance Drone")) {
        	    	   try {
            	    	   Integer batterynumV = Integer.parseInt(batteriesNum1);
            	    	   if(batterynumV<0) {//if the battery value is not correct
            	    		   throw new NumberFormatException(); 
            	    	   }
            	       }catch(NumberFormatException ex){
              	        	JOptionPane.showMessageDialog(null, "Batteries amount should be > 0!", "Invalid Battery Amount",
    	        			        JOptionPane.ERROR_MESSAGE);
           	        	 return;
           	        }
        	    	   //create distance drone to add
        	        DistanceDrone d2 = new DistanceDrone(Integer.parseInt(code1), b, Double.parseDouble(battery1),
        	                Double.parseDouble(maxDistance1), Double.parseDouble(extraPrice1),
        	                Integer.parseInt(batteriesNum1)); 
        	       
        	        
        	        boolean found = false;
        	        //check if drone exists 
        	        for(int i=0;i<systemDataBase.drones.size();i++) {
        	        	if(systemDataBase.drones.get(i).getDroneCode() == Integer.parseInt(code1)){
        	        		 JOptionPane.showMessageDialog(null, "Drone already exists!", "drone exists",
        	        			        JOptionPane.INFORMATION_MESSAGE);
        	        		 found = true;
        	        	}
        	        }
        	        //the drone doesn't exist so we add sit 
        	        if(found == false) {
            	        db.addDrone(d2);
        	        JOptionPane.showMessageDialog(null, "Drone added successfully!", "Success",
        	        JOptionPane.INFORMATION_MESSAGE);
        	        ad.dispose();
        	        }

        	       }
        	       //if the user want's to add a express drone
        	       if(selectedDroneType.equals("2.Express Drone")) {
        	    	   try {
        	    		  Double speedv = Double.parseDouble(speed1);
        	    		  //check if the speed value is valid
        	    		  if(speedv<=0) {
        	    			  throw new NumberFormatException(); 
        	    		  }
        	    		  
        	    	   }catch(NumberFormatException ex){
             	        	JOptionPane.showMessageDialog(null, "Drone speed should be > 0 and a numeric value", "Invalid Drone speed",
   	        			        JOptionPane.ERROR_MESSAGE);
          	        	 return;
          	        }
        	    	   //create drone to add
        	    	   ExpressDrone d3 = new ExpressDrone(Integer.parseInt(code1),b, Double.parseDouble(battery1),Double.parseDouble(speed1),Double.parseDouble(extraPrice1));
        	    	   boolean found = false;
        	    	   //check that the drone does't already exist
        	           for(int i=0;i<systemDataBase.drones.size();i++) {
        	           	if(systemDataBase.drones.get(i).getDroneCode() == Integer.parseInt(code1)){
        	           		 JOptionPane.showMessageDialog(null, "Drone already exists!", "drone exists",
        	           			        JOptionPane.INFORMATION_MESSAGE);
        	           		 found = true;
        	           	}
        	           }
        	           //if the drone exists
        	           if(found == false) {
            	           db.addDrone(d3);
        	           JOptionPane.showMessageDialog(null,"Drone added successfully!", "Success",
        	           JOptionPane.INFORMATION_MESSAGE);
        	           ad.dispose();        	           }

        	       }
        	        
        	
        	}
        	}
        });
        
        prev = new JButton("Previous");
        prev.setFont(new Font("Times New Roman", Font.BOLD, 15));
		prev.setBounds(25, 340, 150, 30);
		prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ad.dispose(); // Close the current frame
                cmf.setVisible(true); // Show the previous frame
            }

        });
        ad.getContentPane().add(prev);
       
        
        
        speedl.setVisible(false);
        speed.setVisible(false);
        extraPricel.setVisible(false);
        extraPrice.setVisible(false);
        maxDistancel.setVisible(false);
        maxDistance.setVisible(false);
        batteriesNuml.setVisible(false);
        batteriesNum.setVisible(false);

        ad.getContentPane().add(batteriesNum);
        ad.getContentPane().add(batteriesNuml);
        ad.getContentPane().add(extraPrice);
        ad.getContentPane().add(extraPricel);
        ad.getContentPane().add(maxDistance);
        ad.getContentPane().add(maxDistancel);
        ad.getContentPane().add(speed);
        ad.getContentPane().add(speedl);
        ad.getContentPane().add(dronet);
        ad.getContentPane().add(DroneType);
        ad.getContentPane().add(codel);
        ad.getContentPane().add(code);
        ad.getContentPane().add(status);
        ad.getContentPane().add(statusl);
        ad.getContentPane().add(battery);
        ad.getContentPane().add(batteryl);
        ad.getContentPane().add(addM);

        cmf.setVisible(true);
        ad.setSize(500, 500);
        ad.getContentPane().setLayout(null);
        ad.setVisible(true);

       
       
    }

    @Override
    public void itemStateChanged(ItemEvent e) {//to show the extra fields based on the drone type 
        if (e.getSource() == DroneType) {
            String str = DroneType.getSelectedItem();
            if (str.equals("2.Express Drone")) {
                speedl.setVisible(true);
                speed.setVisible(true);
                extraPricel.setVisible(true);
                extraPrice.setVisible(true);
                maxDistancel.setVisible(false);
                maxDistance.setVisible(false);
                batteriesNuml.setVisible(false);
                batteriesNum.setVisible(false);
            } else if (str.equals("3.Distance Drone")) {
                maxDistancel.setVisible(true);
                maxDistance.setVisible(true);
                extraPricel.setVisible(true);
                extraPrice.setVisible(true);
                batteriesNuml.setVisible(true);
                batteriesNum.setVisible(true);
                speedl.setVisible(false);
                speed.setVisible(false);
            } else {//regular drone
                speedl.setVisible(false);
                speed.setVisible(false);
                extraPricel.setVisible(false);
                extraPrice.setVisible(false);
                maxDistancel.setVisible(false);
                maxDistance.setVisible(false);
                batteriesNuml.setVisible(false);
                batteriesNum.setVisible(false);
            }
        }
    }

}
