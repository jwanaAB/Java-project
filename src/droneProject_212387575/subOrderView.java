package droneProject_212387575;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class subOrderView {
	protected JFrame sf,sov;
	protected systemDataBase db;
	protected int code;
	protected JButton prev;
	
	subOrderView(JFrame sf, systemDataBase db, int code ){//the code is the sub code
		this.sf= sf;
		this.db= db;
		this.code = code;
		sf.setVisible(true);
		sov = new JFrame("View Orders");
		sov.setVisible(true);
		sov.setSize(500,500);
		sov.getContentPane().setLayout(null);
		int len = systemDataBase.orders.size();
		String[] col = {"Order Details", "Drone Details"};
		String[][] data = new String[len][2];		
		int i=0;
		int k;
		boolean found = true;//to check if there are any orders
		for(k=0; k<len; k++) {
			if(systemDataBase.orders.get(k).getSubCode() == code) {
				found = false;
				data[i][0] = systemDataBase.orders.get(k).toString();//add the order details
				for(int j=0 ; j<systemDataBase.drones.size();j++) {
					if(systemDataBase.drones.get(j).getDroneCode() == systemDataBase.orders.get(k).getDroneCode()) {
						data[i][1] = systemDataBase.drones.get(j).toString();//add the drone details for the order
                     i++;
					}
				}
				
			}
			
		}
		if(found == true) {//if no order was found
			JOptionPane.showMessageDialog(null, "Order not found.", "Invalid order code",
 			        JOptionPane.ERROR_MESSAGE);
			return;
		}
		JTable jtable=new JTable(data,col);
		JScrollPane sp=new JScrollPane(jtable);
		prev = new JButton("Previous");
        prev.setFont(new Font("Times New Roman", Font.BOLD, 15));
		prev.setBounds(25, 340, 150, 30);
		prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sov.dispose(); // Close the current frame
                sf.setVisible(true); // Show the previous frame
            }

        });
        sov.getContentPane().add(prev);
		sp.setLocation(0, 0);
		 sp.setBounds(10, 10, 480, 450); 
	     sov.add(sp);
	}

}
