package droneProject_212387575;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ShowManager {
	protected JFrame cmf,sm;
	protected systemDataBase db;
	protected JButton prev;
	
	
	ShowManager(JFrame cmf, systemDataBase db){
		
		this.db=db;
		this.cmf=cmf;
		sm = new JFrame("View Managers");
		cmf.setVisible(true);
		String[] col = {"Number","First Name","Last Name"}; 
		int size = db.getManagerList().size();
		String[][] data = new String[size][col.length];
		 
		
		int i=0,count=1;
			//adding the manager information to the table		
		for(Manager s: systemDataBase.managerList) {
	
			
			data[i][0]= String.valueOf(count);
			data[i][1] = s.getFirstName();
			data[i][2] = s.getLastName();
			count++;
			i++;
		}	
		
		prev = new JButton("Previous");
		prev.setBounds(50, 400, 100, 30);
		prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sm.dispose(); // Close the current frame
                cmf.setVisible(true); // Show the previous frame
            }

        });
        sm.add(prev);
		
		
		JTable table = new JTable(data,col);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(50, 50, 400, 300); 
	    table.setBounds(0, 0, 400, 300);
	    sm.add(sp);
		sm.setSize(550, 550);
		sm.setVisible(true);
		sm.setLayout(null);
	}
    
}
