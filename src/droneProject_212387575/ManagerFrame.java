package droneProject_212387575;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerFrame {
	JFrame login, order;
	JButton addOrder;
	systemDataBase db;
	ManagerFrame(JFrame login, systemDataBase db){
		this.db = db;
		this.login = login;
		addOrder = new JButton("Create Order");
		addOrder.addActionListener(new ActionListener() {//if the add order button was pressed
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == addOrder) {
        			new addOrder(order,db);//go to the add order frame
        			order.setVisible(false);
        		}
        	}
        	});
		
		order = new JFrame("Create Order");
		order.setSize(500, 550);
		order.getContentPane().setLayout(null);
		addOrder.setBackground(new Color(123, 104, 238));
		addOrder.setFont(new Font("Times New Roman", Font.BOLD, 14));
		addOrder.setBounds(167, 187, 131, 47);
		order.getContentPane().add(addOrder);
		
		JLabel lblNewLabel = new JLabel("New label");//background image
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\win10\\Desktop\\manager.jpg"));
		lblNewLabel.setBounds(0, 0, 500, 520);
		order.getContentPane().add(lblNewLabel);
		order.setVisible(true);
	}
}
