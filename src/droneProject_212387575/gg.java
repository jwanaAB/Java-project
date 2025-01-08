package droneProject_212387575;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;


public class gg {
	
	protected static systemDataBase db = new systemDataBase();
    protected JFrame login = new JFrame("Log in page");
    protected JFrame error = new JFrame("Error");
    protected JTextField usernameField = new JTextField(15);
    protected JPasswordField passwordField = new JPasswordField(15);
    protected JButton loginb = new JButton("log in");
    protected JPanel p;
    protected Choice type;

    gg() {

        p = new JPanel();
        p.setLayout(null);
        
        type = new Choice();
        type.add("Manager");
        type.add("Main Managers");
        type.add("Subscriber");
        p.add(type);
        type.setBounds(330, 205, 100, 40);
        

        JLabel userlabel = new JLabel("Username:");
        userlabel.setBounds(200, 250, 100, 30);
        p.add(userlabel);

        usernameField.setBounds(300, 250, 150, 30);
        p.add(usernameField);

        JLabel passwordlabel = new JLabel("Password:");
        passwordlabel.setBounds(200, 300, 100, 30);
        p.add(passwordlabel);

        passwordField.setBounds(300, 300, 150, 30);
        p.add(passwordField);
        
        type.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String selectedType = type.getSelectedItem();
                
               if(selectedType.equals("Main Managers")) {
            	   new viewDB(login,db);
            	   
               }
            }
        });
        

        loginb.setBounds(330, 370, 80, 30);
        loginb.setFont(new Font("Comic Sans", Font.BOLD,14));
        loginb.setBackground(new Color(240,20,97));
        loginb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean found = false;
                if (e.getSource() == loginb) {
                	 
                    for (int i = 0; i < db.getManagerList().size(); i++) {
                    	if (db.getManagerList().get(i) instanceof ChiefManager ) {
                        if (((String) usernameField.getText()).equals(((ChiefManager) db.getManagerList().get(i)).getUserName())) {
                        	
                            // ChiefManager login
                            if (((String) passwordField.getText()).equals(((ChiefManager) db.getManagerList().get(i)).getPassword())) {
                                
                            	found = true;
                                login.setVisible(false);
                                new cheifManagerFrame(login,db);
                                break;
                            }
                        }
                    }

                        if (db.getManagerList().get(i) instanceof Manager && !(db.getManagerList().get(i) instanceof ChiefManager)) {
                            // Manager login
                            if (((String) usernameField.getText()).equals(((Manager) db.getManagerList().get(i)).getUsername())) {
                                if (((String) passwordField.getText()).equals(((Manager) db.getManagerList().get(i)).getPassword())) {
                                    found = true;
                                    login.setVisible(false);
                                     new ManagerFrame(login,db);
                                }
                            }
                        }
                    }
                    for(Entry<Integer, Subscription> m:db.getSubscribers().entrySet())
                        if (((String) usernameField.getText()).equals((m.getValue().getUsername()))) {
                            // Subscriber login
                            if (((String) passwordField.getText()).equals((m.getValue().getPassword()))) {
                                found = true;
                                login.setVisible(false);
                                String user = ((String) usernameField.getText());
                                for(int i=0; i<systemDataBase.sub.size();i++) {
                                	if(systemDataBase.sub.get(i).getUsername().equals(user)) {
                                		int code = systemDataBase.sub.get(i).getSubCode();
                                		new subscriberFrame(login,db,code);
                                	}
                                	
                                }
                                 
                            }
                        }
                    

                    if (!found) {//if the username or password are not found
                    	JOptionPane.showMessageDialog(error, "Incorrect password or username");
                        System.out.println("Please enter valid username and password");
                    }
                }
            }
        });

	
        p.add(loginb);

        login.setSize(500, 600);
        login.getContentPane().add(p);
        
        JLabel backgroundl = new JLabel("background");
        backgroundl.setIcon(new ImageIcon("C:\\Users\\win10\\Desktop\\bgdrone.jpg"));
        backgroundl.setBounds(0, 0, 500, 600);
        p.add(backgroundl);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(330, 186, 45, 13);
        p.add(lblNewLabel);
        
        JLabel lblType = new JLabel("Type");
        lblType.setText("Choose Type");
        lblType.setBounds(365, 186, 45, 13);
        p.add(lblType);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setBounds(0, 0, 45, 13);
        p.add(lblNewLabel_1);
        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
