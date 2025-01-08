package droneProject_212387575;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Map;

public class editSubInfo {
	protected JFrame sf, esi;
	protected systemDataBase db;
	protected JTextField adress, phoneNumber, subCode;
	protected JLabel adressl, phoneNumberl, subCodel;
	protected JButton changeInfo,prev,ex;
	private JButton update;
	
	
	editSubInfo(JFrame sf, systemDataBase db){
		this.sf= sf;
		this.db= db;
		sf.setVisible(false);
		esi = new JFrame("Edit personal information");
		 //update = new
         update = new JButton("Update Information");	
		update.setBounds(178, 279, 145, 36);
		changeInfo = new JButton("Update Information");
		changeInfo.setBackground(new Color(72, 61, 139));
		changeInfo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		changeInfo.setSize(163, 51);
		changeInfo.setLocation(229, 309);
		changeInfo.setBounds(178, 345, 161, 53);
		changeInfo.setVisible(true);
		adress = new JTextField(20);
		adress.setSize(153, 27);
		adress.setLocation(239, 155);
		phoneNumber = new JTextField(10);
		phoneNumber.setSize(153, 27);
		phoneNumber.setLocation(239, 212);
		subCode =  new JTextField(10); 
		subCode.setSize(153, 27);
		subCode.setLocation(239, 91);
		adressl = new JLabel("New Adress:");
		adressl.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		adressl.setSize(142, 42);
		adressl.setLocation(59, 147);
		phoneNumberl = new JLabel("New Phone Number:");
		phoneNumberl.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		phoneNumberl.setSize(120, 42);
		phoneNumberl.setLocation(59, 212);
		subCodel = new JLabel("Enter Subscription Code:");
		subCodel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		subCodel.setSize(142, 42);
		subCodel.setLocation(59, 83);
		esi.getContentPane().add(subCode);
		esi.getContentPane().add(subCodel);
		esi.getContentPane().add(adress);
		esi.getContentPane().add(adressl);
		esi.getContentPane().add(phoneNumber);
		esi.getContentPane().add(phoneNumberl);
		esi.getContentPane().add(changeInfo);
		update.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == update) {
        			String adressS = adress.getText();
        			String phoneNumberS = phoneNumber.getText();
        			String subCodeS = subCode.getText();
        			if(adressS==""|| phoneNumberS==""|| subCodeS=="") {
        				 JOptionPane.showMessageDialog(null, "Make sure all fields are filled!", "empty fields",
        	                     JOptionPane.ERROR_MESSAGE);
        	             return;
        			}
        			try {
        				int phoneNumber = Integer.parseInt(phoneNumberS); 
        			}catch (NumberFormatException ex) {
        			    JOptionPane.showMessageDialog(null, "Invalid phone number format! Please enter a valid number.", "Invalid Input",
        			            JOptionPane.ERROR_MESSAGE);
        			    return; 
        			}
        			
        			try {//check to see if the input is correct
        				Integer code = Integer.parseInt(subCodeS);
        				
        				for(int i=0;i<systemDataBase.sub.size();i++) {//update the info in the the sub arraylist
        					if(systemDataBase.sub.get(i).getSubCode() == code){
        						systemDataBase.sub.get(i).setAddress(adressS);
        						systemDataBase.sub.get(i).setPhone(phoneNumberS);
        						
        					}
        				}
        			    Hashtable<Integer,Subscription> updateSub = systemDataBase.subscribers;
        			    //update the information also in the hashtable data base 
        			    for(Map.Entry<Integer,Subscription> m : updateSub.entrySet()) {
        			    	if(m.getValue().getSubCode()== code) {
        			    		m.getValue().setAddress(adressS);
        			    		m.getValue().setPhone(phoneNumberS);
        			    	}
        			    }
        			    
        			    JOptionPane.showMessageDialog(null, "Information changed!", "success",
       	                     JOptionPane.INFORMATION_MESSAGE);
       	             return;
        				
        			}catch(NumberFormatException ex) {
        				 JOptionPane.showMessageDialog(null, "Incorrect Subscription Code!", "not found",
        	                     JOptionPane.ERROR_MESSAGE);
        	             return;
        			}
        		}
        	}
        	
        });
		
		/*prev = new JButton("Previous");
		prev.setBackground(new Color(250, 128, 114));
        prev.setFont(new Font("Times New Roman", Font.BOLD, 15));
		prev.setBounds(300, 310, 150, 51);
		prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                esi.dispose(); // Close the current frame
                sf.setVisible(true); // Show the previous frame
            }

        });
        esi.getContentPane().add(prev);*/
						
		esi.setVisible(true);
		esi.setSize(500,500);
		esi.getContentPane().setLayout(null);
		
		
		esi.getContentPane().add(update);
		
		
	}

}
