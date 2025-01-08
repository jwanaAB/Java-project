package droneProject_212387575;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;

public class addOrder {

	protected JFrame mf, order;
	protected JButton addOrder, clearButton,prev;
	protected JLabel subCode, droneCode, Day, Hour;
	protected JTextField subCodet, droneCodet, Dayt, Hourt;
	protected systemDataBase db;

    addOrder(JFrame mf, systemDataBase db) {
        this.db = db;
        this.mf = mf;
        addOrder = new JButton("Add Order");
        addOrder.setBackground(new Color(147, 112, 219));
        addOrder.setFont(new Font("Times New Roman", Font.BOLD, 15));
        addOrder.setSize(126, 49);
        addOrder.setLocation(100, 308);

        subCode = new JLabel("Enter Subscription Code:");
        subCode.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        subCode.setSize(154, 40);
        subCode.setLocation(36, 60);
        droneCode = new JLabel("Enter Drone Code:");
        droneCode.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        droneCode.setSize(114, 49);
        droneCode.setLocation(36, 110);
        Day = new JLabel("Enter Day:");
        Day.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        Day.setSize(75, 49);
        Day.setLocation(36, 166);
        Hour = new JLabel("Enter Hour:");
        Hour.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        Hour.setSize(75, 60);
        Hour.setLocation(36, 213);

        subCodet = new JTextField(20);
        subCodet.setSize(126, 20);
        subCodet.setLocation(216, 71);
        droneCodet = new JTextField(20);
        droneCodet.setSize(126, 20);
        droneCodet.setLocation(216, 124);
        Dayt = new JTextField(20);
        Dayt.setSize(126, 22);
        Dayt.setLocation(216, 180);
        Hourt = new JTextField(20);
        Hourt.setSize(126, 22);
        Hourt.setLocation(216, 234);

        clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(30, 144, 255));
        clearButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        clearButton.setSize(126, 49);
        clearButton.setLocation(290, 308);

        order = new JFrame("Create Order");
        order.setSize(500, 550);

        //if the manager wants to add a new order
        addOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addOrder) {
                    String dCode = droneCodet.getText();
                    String subCode1 = subCodet.getText();
                    String Day1 = Dayt.getText();
                    String Hour1 = Hourt.getText();
                    try {//check if all the entered values are correct
                        Integer Dronecode1 = Integer.parseInt(dCode);
                        Integer SubscriptionCode = Integer.parseInt(subCode1);
                        Integer Dayy = Integer.parseInt(Day1);
                        Integer Hourr = Integer.parseInt(Hour1);

                        Drone d = db.findDroneByCode(Dronecode1);
                        if (d.status == false) {
                            JOptionPane.showMessageDialog(null, "Drone not available!", "unavailable",
                                    JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                        ArrayList<Manager> m = db.ManagerDrones(d);
                        if (m == null) {
                            JOptionPane.showMessageDialog(null, "Manager not responsible for drone!", "not found",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        boolean found = false;
                        for (Manager manager : m) {
                            if (manager.isDroneResponsible(d) == true) {
                                found = true;
                                int code = systemDataBase.generateOrderCode();
                                boolean orderExists = false;//check if order exists before adding it 
                                for (int i = 0; i < systemDataBase.orders.size(); i++) {
                                    if (systemDataBase.orders.get(i).getDroneCode() == Dronecode1) {
                                        JOptionPane.showMessageDialog(null, "Order already exists!", "found",
                                                JOptionPane.INFORMATION_MESSAGE);
                                        orderExists = true;
                                        System.out.println("not found");
                                        break;
                                    }
                                }
                                if (orderExists) {
                                    return; // Exit the method if the order already exists
                                }
                                Order order = new Order(code, Dayy, 3, Hourr, 12,
                                        SubscriptionCode, Dronecode1, systemDataBase.calculateExtraOrderPrice(d));
                                db.addOrderToManager(order, manager);
                                // db.addOrderToOrders(order);
                                db.addOrderToTable(order);
                                // Display "Order Added Successfully" message only if the order is successfully added
                                JOptionPane.showMessageDialog(null, "Order Added Successfully!", "added",
                                        JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }

                        }
                        if (found == true) {
                            JOptionPane.showMessageDialog(null, "Manager not responsible for drone!", "not found",
                                    JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter valid numeric values.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clearing text fields when clear button is clicked
                subCodet.setText("");
                droneCodet.setText("");
                Dayt.setText("");
                Hourt.setText("");
            }
        });
        prev = new JButton("Previous");
        prev.setBackground(new Color(221, 160, 221));
        prev.setFont(new Font("Times New Roman", Font.BOLD, 15));
		prev.setBounds(192, 388, 126, 49);
		prev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                order.dispose(); // Close the current frame
                mf.setVisible(true); // Show the previous frame
            }

        });
        order.getContentPane().add(prev);

        order.getContentPane().add(clearButton);
        order.getContentPane().add(addOrder);
        order.getContentPane().add(Day);
        order.getContentPane().add(Dayt);
        order.getContentPane().add(Hour);
        order.getContentPane().add(Hourt);
        order.getContentPane().add(droneCode);
        order.getContentPane().add(droneCodet);
        order.getContentPane().add(subCode);
        order.getContentPane().add(subCodet);

        order.getContentPane().setLayout(null);
        order.setVisible(true);
    }
}
