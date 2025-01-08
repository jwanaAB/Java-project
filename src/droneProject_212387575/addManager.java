package droneProject_212387575;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class addManager {
    protected JFrame cmf, am;
    protected systemDataBase db;
    protected Choice managerType = new Choice();
    protected JLabel usernamel, passwordl, idl, firstnamel, lastnamel, managerTypel;
    protected JTextField username, password, id, firstname, lastname;
    protected JButton addM,prev;

    public addManager(JFrame cmf, systemDataBase db) {
        this.db = db;
        this.cmf = cmf;
        managerType.add("1.General Manager");
        managerType.add("2.Chief Manager");
        am = new JFrame("Add Manager");
        usernamel = new JLabel("Username: ");
        usernamel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        passwordl = new JLabel("Password: ");
        passwordl.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        idl = new JLabel("Id: ");
        idl.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        firstnamel = new JLabel("First Name: ");
        firstnamel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lastnamel = new JLabel("Last Name: ");
        lastnamel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        managerTypel = new JLabel("Choose Manager Type: ");
        managerTypel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        username = new JTextField(20);
        password = new JTextField(20);
        id = new JTextField(11);
        firstname = new JTextField(20);
        lastname = new JTextField(20);
        addM = new JButton("Add Manager To System");
        addM.setFont(new Font("Times New Roman", Font.BOLD, 15));

        // Set bounds for components
        managerTypel.setBounds(50, 50, 150, 30); 
        managerType.setBounds(200, 50, 200, 30); 
        usernamel.setBounds(50, 100, 100, 30);
        passwordl.setBounds(50, 150, 100, 30);
        idl.setBounds(50, 200, 100, 30);
        firstnamel.setBounds(50, 250, 100, 30);
        lastnamel.setBounds(50, 300, 100, 30);

        username.setBounds(200, 100, 200, 30);
        password.setBounds(200, 150, 200, 30);
        id.setBounds(200, 200, 200, 30);
        firstname.setBounds(200, 250, 200, 30);
        lastname.setBounds(200, 300, 200, 30);

        addM.setBounds(200, 350, 200, 30);

        // Add action listener to the button
        addM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Add Manager To System")) {
                    String id1 = id.getText();
                    String fn = firstname.getText();
                    String ln = lastname.getText();
                    String una = username.getText();
                    String p = password.getText();
                    String selectedManagerType = managerType.getSelectedItem();
                    if (id1.isEmpty() || fn.isEmpty() || ln.isEmpty() || una.isEmpty() || p.isEmpty() || selectedManagerType == null) {
                        JOptionPane.showMessageDialog(null, "Make sure all fields are filled!", "Empty Fields",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    try {//check if the entered id is valid
                    	Integer idV = Integer.parseInt(id1);
                    }catch (NumberFormatException ex){
        				JOptionPane.showMessageDialog(null, "Invalid ID. Please enter numeric numbers.", "Invalid ID",
 	        			        JOptionPane.ERROR_MESSAGE);
        				return;
        			}
                    //check if the manager already exist
                    boolean found = true;
                  for(int i=0;i<db.getManagerList().size();i++) {
                	  if(db.getManagerList().get(i).getId().equals(id1)) {
                		  JOptionPane.showMessageDialog(null, "Manager already exists!", "manager exists",
  	        			        JOptionPane.INFORMATION_MESSAGE);
                		  found=false;
                	  }
                  }
                  if(found) {
                    // Create Manager object based on the selected manager type
                    Manager m;
                    if (selectedManagerType.equals("1.General Manager")) {
                    	System.out.println("pppp");
                        m = new Manager(id1, fn, ln, null, null, una, p);  
                        db.addManager(m);


                    } else {
                        m = new ChiefManager(id1, fn, ln, null, null, una, p);
                        db.addManager(m);
                        systemDataBase.managerList.add(m);
                    }

                   // db.manager
                    JOptionPane.showMessageDialog(null, "Manager added successfully!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    am.dispose(); // Close the addManager frame after adding the manager
                    }
                }
            }
        });
        
        prev = new JButton("Previous");
        prev.setFont(new Font("Times New Roman", Font.BOLD, 15));
		prev.setBounds(21, 350, 140, 30);
		prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                am.dispose(); // Close the current frame
                cmf.setVisible(true); // Show the previous frame
            }

        });
        am.getContentPane().add(prev);

        // Add components to the frame
        am.getContentPane().add(managerTypel); 
        am.getContentPane().add(managerType); 
        am.getContentPane().add(usernamel);
        am.getContentPane().add(passwordl);
        am.getContentPane().add(idl);
        am.getContentPane().add(firstnamel);
        am.getContentPane().add(lastnamel);
        am.getContentPane().add(username);
        am.getContentPane().add(password);
        am.getContentPane().add(id);
        am.getContentPane().add(firstname);
        am.getContentPane().add(lastname);
        am.getContentPane().add(addM);

        cmf.setVisible(true);
        am.setSize(450, 450);
        am.getContentPane().setLayout(null);
        am.setVisible(true);
    }
}
