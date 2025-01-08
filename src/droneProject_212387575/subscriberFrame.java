package droneProject_212387575;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class subscriberFrame {
	protected JFrame lf, subscriberF;
	protected systemDataBase db;
	protected JButton viewOrder, editInfo,prev;
	protected int code;
	private JLabel lblNewLabel;

	subscriberFrame(JFrame lf, systemDataBase db, int code){
		this.lf=lf;
		this.db=db;
		this.code = code;
		lf.setVisible(false);
		subscriberF = new JFrame("Subscriber log in");
		subscriberF.getContentPane().setLayout(null);
		subscriberF.setSize(450, 349);
		subscriberF.setVisible(true);
		
		viewOrder = new JButton("View Orders");
		viewOrder.setBackground(new Color(123, 104, 238));
		viewOrder.setFont(new Font("Times New Roman", Font.BOLD, 14));
		viewOrder.setSize(132, 48);
		viewOrder.setLocation(274, 34);
		editInfo = new JButton("Edit Personal Information");
		editInfo.setBackground(new Color(123, 104, 238));
		editInfo.setFont(new Font("Times New Roman", Font.BOLD, 13));
		editInfo.setSize(169, 48);
		editInfo.setLocation(257, 240);
		//if the view order button was pressed
		viewOrder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == viewOrder) {
        			subscriberF.setVisible(true);
        			new subOrderView(subscriberF,db,code);
        		}
        	}
        	
        });
		//if the edit personal information was pressed
		editInfo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == editInfo) {
        			subscriberF.setVisible(true);
        			new editSubInfo(subscriberF,db);
        		}
        	}
        	
        });
		
		subscriberF.getContentPane().add(viewOrder);
		subscriberF.getContentPane().add(editInfo);
		
		lblNewLabel = new JLabel("New label");//background image
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\win10\\Desktop\\sub-photo.jpg"));
		lblNewLabel.setBounds(0, -27, 440, 360);
		subscriberF.getContentPane().add(lblNewLabel);
		
	}
}
