package droneProject_212387575;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ShowDrone {
	
	protected JFrame cmf,sd;
	protected systemDataBase db;
	protected JButton prev;
	
	ShowDrone(JFrame cmf, systemDataBase db){

		this.db=db;
		this.cmf=cmf;
		sd = new JFrame("View Drones");
		cmf.setVisible(true);
		String[] col = {"Code","Type","Status"}; 
		int size = db.getDrones().size();
		String[][] data = new String[size][col.length];
		 
		
		int i=0,count=1;
			
		//adding the drone info to the table
		for(Drone s: systemDataBase.drones) {
	
			
			data[i][0]= String.valueOf(s.getDroneCode());
			if(s instanceof ExpressDrone) {
				data[i][1] = "Express Drone";
			}
			else if(s instanceof DistanceDrone) {
				data[i][1] = "Distance Drone";

			}
			else {
				data[i][1] = "Regular Drone";

			}
			
			data[i][2] = String.valueOf(s.status);
			count++;
			i++;
		}	
		
		
		prev = new JButton("Previous");
		prev.setBounds(50, 400, 100, 30);
		prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sd.dispose(); // Close the current frame
                cmf.setVisible(true); // Show the previous frame
            }

        });
        sd.add(prev);
		
		
		JTable table = new JTable(data,col);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(50, 50, 400, 300); 
	    table.setBounds(0, 0, 400, 300);
	    sd.add(sp);
	    sd.setSize(550, 550);
	    sd.setVisible(true);
	    sd.setLayout(null);
	}

}
