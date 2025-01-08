package droneProject_212387575;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.*;

public class ShowSub {
	protected JFrame ss,cml;
	protected systemDataBase db;
	protected JButton prev;
	
	ShowSub(JFrame cml, systemDataBase db){
		ss= new JFrame("View Subscribers");
		this.cml=cml;
		this.db=db;
		cml.setVisible(true);
		
		String[] col = {"Number","Last Name","First Name"}; 
		int size = db.getSubscribers().entrySet().size();
		String[][] data = new String[size][col.length];
		 
		
		int i=0,count=1;
		//adding the sub information to the table			
		for(Subscription s: systemDataBase.sub) {
	
			
			data[i][0]= String.valueOf(count);
			data[i][1] = s.getLastName();
			data[i][2] = s.getFirstName();
			count++;
			i++;
		}	
		
		prev = new JButton("Previous");
		prev.setBounds(50, 400, 100, 30);
		prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ss.dispose(); // Close the current frame
                cml.setVisible(true); // Show the previous frame
            }

        });
        ss.add(prev);
		
		
		JTable table = new JTable(data,col);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(50, 50, 400, 300); 
	     table.setBounds(0, 0, 400, 300);
		ss.add(sp);
		ss.setLayout(null);
		ss.setSize(550, 550);
		ss.setVisible(true);

	
	}

}
