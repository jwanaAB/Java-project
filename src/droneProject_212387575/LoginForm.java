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

/*
 * I put the db in a txt file called AllManagers
 * 
 * 
 * 
 * 
 * */
public class LoginForm {
	
	protected static systemDataBase db = new systemDataBase();
    protected JFrame login = new JFrame("Log in page");
    protected JFrame error = new JFrame("Error");
    protected JTextField usernameField = new JTextField(15);
    protected JPasswordField passwordField = new JPasswordField(15);
    protected JButton loginb = new JButton("log in");
    protected JButton showDBButton = new JButton("Show Database");
    protected JPanel p;
    protected Choice type;

    LoginForm() {

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

                if (selectedType.equals("Main Managers")) {
                    p.add(showDBButton); // Add the showDBButton when Chief Manager is selected
                    showDBButton.setBounds(420, 370, 150, 30);
                    showDBButton.setBackground(new Color(128, 0, 128)); // Set button color to purple
                    showDBButton.setForeground(Color.WHITE); // Set text color to white
                    showDBButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            new viewDB(login, db);
                        }
                    });
                } else {
                    p.remove(showDBButton); // Remove the showDBButton if another type is selected
                }
            }
        });

        loginb.setBounds(330, 370, 80, 30);
        loginb.setFont(new Font("Comic Sans", Font.BOLD, 14));
        loginb.setBackground(new Color(240, 20, 97));
        loginb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean found = false;
                if (e.getSource() == loginb) {

                    for (int i = 0; i < db.getManagerList().size(); i++) {
                        if (db.getManagerList().get(i) instanceof ChiefManager) {
                            if (((String) usernameField.getText()).equals(((ChiefManager) db.getManagerList().get(i)).getUserName())) {

                                // ChiefManager login
                                if (((String) passwordField.getText()).equals(((ChiefManager) db.getManagerList().get(i)).getPassword())) {

                                    found = true;
                                    login.setVisible(false);
                                    new cheifManagerFrame(login, db);
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
                                    new ManagerFrame(login, db);
                                }
                            }
                        }
                    }
                    for (Entry<Integer, Subscription> m : db.getSubscribers().entrySet())
                        if (((String) usernameField.getText()).equals((m.getValue().getUsername()))) {
                            // Subscriber login
                            if (((String) passwordField.getText()).equals((m.getValue().getPassword()))) {
                                found = true;
                                login.setVisible(false);
                                String user = ((String) usernameField.getText());
                                for (int i = 0; i < systemDataBase.sub.size(); i++) {
                                    if (systemDataBase.sub.get(i).getUsername().equals(user)) {
                                        int code = systemDataBase.sub.get(i).getSubCode();
                                        new subscriberFrame(login, db, code);
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

        login.setSize(600, 500);
        login.getContentPane().add(p);

        JLabel backgroundl = new JLabel("background");
        backgroundl.setIcon(new ImageIcon("C:\\Users\\win10\\Desktop\\bgdrone.jpg"));
        backgroundl.setBounds(0, 0, 600, 500);
        p.add(backgroundl);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(330, 186, 45, 13);
        p.add(lblNewLabel);

        JLabel lblType = new JLabel("Type");
        lblType.setText("Choose Type");
        lblType.setBounds(300, 186, 45, 13);
        p.add(lblType);

        login.setVisible(true);
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {//filling the system data base

    	Drone d = new Drone(1, true, 100.0);
        ChiefManager mainAdmin1= new ChiefManager("1", "Ross", "Geller", null, null, "system", "12345");
        ArrayList<Manager> managers = new ArrayList<Manager>();
        managers.add(mainAdmin1);
        ChiefManager mainAdmin2 = new ChiefManager("6", "Amal", "Awad", null, null, "ad5", "password6");
        Manager admin3 = new Manager("7", "chris", "Bei", null, null,"ll","ll");
        Manager admin4 = new Manager("8", "Jojo", "Tiana", null, null,"kk","kk");
        Manager admin5 = new Manager("9", "Rawan", "Awad", null, null,"hh","hh");
        Manager admin6 = new Manager( "122","luna","ee" ,null, null, "lop" ,"lop");
        Manager admin7 = new Manager("14", "grace", "ab", null,null , "you", "tt");
        Order to = new Order(90 ,1 ,2 ,4 ,8 ,2 ,2 ,10.0);
        admin7.addOrder(to);
        admin6.addDrone(d);
        
        managers.add(mainAdmin2);
        managers.add(admin3);
        managers.add(admin4);
        managers.add(admin5);
        managers.add(admin7);
        managers.add(admin6);
        db.setChiefManager(mainAdmin1);
        db.setManagerList(managers);     
       
        
        // Add administrators to the administrators' array
        
      Collections.sort(systemDataBase.managerList);
       
        // ourSystem = new systemDataBase(managers);

        // Create an array of subscribers
        Subscription subscriber1 = new Subscription(1,"Phoebe", "Buffay", "Address1", "123456789","name", "name");
        Subscription subscriber2 = new Subscription(2,"Ross", "Geller", "Address2", "987654321","111","111");
        Subscription subscriber3 = new Subscription(3,"Monica", "Geller", "Address3", "111223344","222","222");
        Subscription subscriber4 = new Subscription(4,"Joey", "Tribbiani", "Address4", "555666777","333","333");
        Subscription subscriber5 = new Subscription(5,"Rachel", "Green", "Address5", "999888777","444","444");
        Subscription subscriber6 = new Subscription(6,"Chandler", "Bing", "Address6", "111223344","55","55");
        Subscription subscriber7 = new Subscription(7,"Janice", "Hosenstein", "Address7", "555666777","66","66");
        Subscription subscriber8 = new Subscription(8,"Gunther", "CoffeeGuy", "Address8", "999888777","77","77");
        Subscription subscriber9 = new Subscription(9,"Ursula", "Buffay", "Address9", "111223344","88","88");
        Subscription subscriber10 = new Subscription(10,"Richard", "Burke", "Address10", "555666777","99","99");
      
        // Add subscribers to the subscribers' ArrayList
        
       db.addSubscriber(subscriber1);
       db.addSubscriber(subscriber2);
       db.addSubscriber(subscriber3);
       db.addSubscriber(subscriber4);
       db.addSubscriber(subscriber5);
       db.addSubscriber(subscriber6);
       db.addSubscriber(subscriber7);
       db.addSubscriber(subscriber8);
       db.addSubscriber(subscriber9);
       db.addSubscriber(subscriber10);



       systemDataBase.sub.add(subscriber1);
       systemDataBase.sub.add(subscriber2);
       systemDataBase.sub.add(subscriber3);
       systemDataBase.sub.add(subscriber4);
       systemDataBase.sub.add(subscriber5);
       systemDataBase.sub.add(subscriber6);
       systemDataBase.sub.add(subscriber7);
       systemDataBase.sub.add(subscriber8);
       systemDataBase.sub.add(subscriber9);
       systemDataBase.sub.add(subscriber10);

       Collections.sort(systemDataBase.sub);
        
        // Create an ArrayList of drones
        ArrayList<Drone> drones = new ArrayList<Drone>(5);
     
        boolean status;
        for (int i = 0; i < 5; i++) {
            // Example: create 10 different drones
            if (i < 2) {
            	status =(i%2==0);           
                drones.add(i, new Drone(i + 1, status, 100.0 - i * 5)); 
            }
            // Example: create 10 different express drones
            else if (i < 3) {
            	status =(i%2!=0);
            	drones.add(i, new ExpressDrone(i + 1, status, 80.0 - (i - 10) * 4, 50.0 - (i - 10) * 2, 10.0 - (i - 10)));

            }
            // Example: create 10 different distance drones
            else {
            	status =(i%2==0);
            	drones.add(i, new DistanceDrone(i + 1, status, 90.0 - (i - 20) * 4, 200.0 - (i - 20) * 10, 15.0 - (i - 20) * 0.5, 2));

            }
        }

        // Add drones to the drones' array
        
        for (Drone drone : drones) {
        	db.addDrone(drone);
        }
        Collections.sort( db.getDrones());//sort the drones based on drone code      
        db.getManagerList().get(1).addDrone(drones.get(0));//adding a drone to the system chief manager
        db.getManagerList().get(0).addDrone(drones.get(1));
       // db.getManagerList().get(0).addDrone(drones.get(3));
     
        db.getDrone(0).addManager(mainAdmin2);
        db.getDrone(1).addManager(mainAdmin1);//use this to check add drone to manager
       // db.getDrone(3).addManager(admin4);
       // db.getDrone(2).addManager(admin6);
        //db.getDrone(4).addManager(admin3);
        //db.getDrone(9).addManager(admin2);
     
        Order order = new Order(33,3,3,3,3,3,3,3);
        Order order2 = new Order(4,4,4,4,4,4,4,4);
       Order order3 = new Order(15,5,5,5,5,5,5,5);
       
       Order order4 = new Order(6,6,6,6,6,6,6,6);
        Order order5 = new Order(7,7,7,7,7,7,7,7);

        //db.getOrders().add(order);

        db.getManagerList().get(0).addOrder(order);
        db.getManagerList().get(3).addOrder(order2);
        db.getManagerList().get(1).addOrder(order3);
        db.getManagerList().get(4).addOrder(order4);
        db.getManagerList().get(4).addOrder(order5);

        db.addOrderToTable(order);
        db.addOrderToTable(order2);
        db.addOrderToTable(order3);
       



    	new LoginForm();
    	
    	}
}
