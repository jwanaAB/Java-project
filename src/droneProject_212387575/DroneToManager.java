package droneProject_212387575;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class DroneToManager {
	
    protected JLabel ManagerCode, DroneCode;
    protected JTextField ManagerCodeT, DroneCodeT;
    protected JButton addDroneToManager,prev;
    protected JFrame dtm, cmf;
    protected systemDataBase db;

    public DroneToManager(JFrame cmf, systemDataBase db) {
        dtm = new JFrame();
        this.cmf = cmf;
        this.db = db;

        ManagerCode = new JLabel("Manager ID:");
        ManagerCode.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        ManagerCode.setSize(77, 32);
        ManagerCode.setLocation(62, 144);

        DroneCode = new JLabel("Drone Code:");
        DroneCode.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        DroneCode.setSize(66, 23);
        DroneCode.setLocation(62, 82);

        ManagerCodeT = new JTextField();
        ManagerCodeT.setSize(115, 23);
        ManagerCodeT.setLocation(171, 149);

        DroneCodeT = new JTextField();
        DroneCodeT.setSize(115, 23);
        DroneCodeT.setLocation(171, 82);

        addDroneToManager = new JButton("Add Drone");
        addDroneToManager.setForeground(new Color(0, 0, 0));
        addDroneToManager.setBackground(new Color(51, 204, 102));
        addDroneToManager.setFont(new Font("Times New Roman", Font.BOLD, 12));
        
        addDroneToManager.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addDroneToManager) {
                    String id = ManagerCodeT.getText();
                    String idd = DroneCodeT.getText();
                    try {//check if the entered input is valid
                        Integer.parseInt(id);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Manager ID. Please enter numeric numbers.", "Invalid ID",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    try {
                        Integer.parseInt(idd);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Drone Code. Please enter numeric numbers.", "Invalid Drone Code",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                        //create the manager and drone to add
                    Manager manager = db.findManagerById(Integer.parseInt(id));
                    Drone drone = db.findDroneByCode(Integer.parseInt(idd));

                    //check if the manager isn't already responsible for the drone
                    if (manager != null && drone != null) {
                        if (manager.getManagerDrones().contains(drone)) {
                            JOptionPane.showMessageDialog(null, "Manager is already responsible for this drone.", "Duplicate Entry",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {//add the drone to the manger
                            manager.addDrone(drone);
                            JOptionPane.showMessageDialog(null, "Drone added to manager successfully!", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {//id the manager or drone were not found
                        JOptionPane.showMessageDialog(null, "Manager or drone does not exist!", "Not Found",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        addDroneToManager.setSize(127, 44);
        addDroneToManager.setLocation(205, 264);
        
        prev = new JButton("Previous");
        prev.setBackground(new Color(216, 191, 216));
        prev.setFont(new Font("Times New Roman", Font.BOLD, 12));
		prev.setBounds(35, 264, 127, 44);
		prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dtm.dispose(); // Close the current frame
                cmf.setVisible(true); // Show the previous frame
            }

        });
        dtm.getContentPane().add(prev);

        dtm.getContentPane().add(DroneCode);
        dtm.getContentPane().add(DroneCodeT);
        dtm.getContentPane().add(ManagerCode);
        dtm.getContentPane().add(ManagerCodeT);
        dtm.getContentPane().add(addDroneToManager);
        dtm.getContentPane().setLayout(null);
        dtm.setSize(450, 500);
        dtm.setVisible(true);
    }
}
