package droneProject_212387575;

import javax.swing.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;


public class Calculator {
	

	 private JTextField tocalc;
	 protected Double num1,num2,res;
	 protected String operator;
	 protected JButton prev;

	
	protected JFrame cmf,calc;
	protected systemDataBase db;
	Calculator(JFrame cmf, systemDataBase db){
		
		this.cmf=cmf;
		this.db=db;
		calc = new JFrame("Calculator");
		calc.getContentPane().setLayout(null);
		calc.setVisible(true);

		calc.setForeground(Color.GRAY);
		calc.getContentPane().setForeground(Color.GRAY);
		calc.setBounds(100, 100, 451, 603);
		
			        tocalc = new JTextField();
			        tocalc.setFont(new Font("Tahoma", Font.PLAIN, 15));
			        tocalc.setBounds(10, 75, 194, 33);
			        calc.getContentPane().add(tocalc);
			        tocalc.setColumns(10);

			        JLabel lblClassicCalculator = new JLabel("Classic Calculator");
			        lblClassicCalculator.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
			        lblClassicCalculator.setBounds(115, 10, 222, 55);
			        calc.getContentPane().add(lblClassicCalculator);
				
				  
			        Button star = new Button("*");
			        star.setForeground(Color.BLACK);
			        star.setFont(new Font("Dialog", Font.BOLD, 35));
			        star.setBounds(352, 136, 60, 60);
			        calc.getContentPane().add(star);
			        star.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			               num1 = Double.parseDouble(tocalc.getText());
			               tocalc.setText(null);
			               operator = "*";
			               
			            }
			        });

			        Button divide = new Button("/");
			        divide.setForeground(Color.BLACK);
			        divide.setFont(new Font("Dialog", Font.BOLD, 35));
			        divide.setBounds(352, 195, 60, 60);
			        calc.getContentPane().add(divide);
			        divide.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			            	 num1 = Double.parseDouble(tocalc.getText());
				               tocalc.setText(null);
				               operator = "/";
			            }
			        });

			        Button plus = new Button("+");
			        plus.setForeground(Color.BLACK);
			        plus.setFont(new Font("Dialog", Font.BOLD, 35));
			        plus.setBounds(352, 261, 60, 60);
			        calc.getContentPane().add(plus);
			        plus.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			                //tocalc.setText(tocalc.getText() + "+");
			            	 num1 = Double.parseDouble(tocalc.getText());
				               tocalc.setText(null);
				               operator = "+";
			            }
			        });

			        Button minus = new Button("-");
			        minus.setForeground(Color.BLACK);
			        minus.setFont(new Font("Dialog", Font.BOLD, 35));
			        minus.setBounds(352, 327, 60, 60);
			        calc.getContentPane().add(minus);
			        minus.addActionListener(new ActionListener() {
			            @Override
			            public void actionPerformed(ActionEvent e) {
			            	 num1 = Double.parseDouble(tocalc.getText());
				               tocalc.setText(null);
				               operator = "-";
			            }
			        });

				Button one = new Button("1");
				one.setForeground(Color.BLACK);
				one.setFont(new Font("Dialog", Font.BOLD, 35));
				one.setBounds(43, 129, 60, 60);
				calc.getContentPane().add(one);
				one.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						 String digit = tocalc.getText() + one.getLabel();
						 tocalc.setText(digit);
						 
						
					}
				});
				
				
				Button two = new Button("2");
				two.setForeground(Color.BLACK);
				two.setFont(new Font("Dialog", Font.BOLD, 35));
				two.setBounds(103, 129, 60, 60);
				calc.getContentPane().add(two);
				two.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String digit = tocalc.getText() + two.getLabel();
						 tocalc.setText(digit);
						
					}
				});
				
				Button three = new Button("3");
				three.setForeground(Color.BLACK);
				three.setFont(new Font("Dialog", Font.BOLD, 35));
				three.setBounds(169, 129, 60, 60);
				calc.getContentPane().add(three);
				three.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String digit = tocalc.getText() + three.getLabel();
						 tocalc.setText(digit);
					}
				});

				Button four = new Button("4");
				four.setForeground(Color.BLACK);
				four.setFont(new Font("Dialog", Font.BOLD, 35));
				four.setBounds(43, 195, 60, 60);
				calc.getContentPane().add(four);
				four.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String digit = tocalc.getText() + four.getLabel();
						 tocalc.setText(digit);
						
					}
				});
				
				Button five = new Button("5");
				five.setForeground(Color.BLACK);
				five.setFont(new Font("Dialog", Font.BOLD, 35));
				five.setBounds(103, 195, 60, 60);
				calc.getContentPane().add(five);
				five.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String digit = tocalc.getText() + five.getLabel();
						 tocalc.setText(digit);
						
					}
				});
				
				Button six = new Button("6");
				six.setForeground(Color.BLACK);
				six.setFont(new Font("Dialog", Font.BOLD, 35));
				six.setBounds(169, 195, 60, 60);
				calc.getContentPane().add(six);
				six.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String digit = tocalc.getText() + six.getLabel();
						 tocalc.setText(digit);
						
					}
				});
				
				Button seven = new Button("7");
				seven.setForeground(Color.BLACK);
				seven.setFont(new Font("Dialog", Font.BOLD, 35));
				seven.setBounds(43, 261, 60, 60);
				calc.getContentPane().add(seven);
				seven.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String digit = tocalc.getText() + seven.getLabel();
						 tocalc.setText(digit);
					}
				});
				
				Button eight = new Button("8");
				eight.setForeground(Color.BLACK);
				eight.setFont(new Font("Dialog", Font.BOLD, 35));
				eight.setBounds(103, 261, 60, 60);
				calc.getContentPane().add(eight);
				eight.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String digit = tocalc.getText() + eight.getLabel();
						 tocalc.setText(digit);
					}
				});
				
				Button nine = new Button("9");
				nine.setForeground(Color.BLACK);
				nine.setFont(new Font("Dialog", Font.BOLD, 35));
				nine.setBounds(169, 261, 60, 60);
				calc.getContentPane().add(nine);
				nine.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String digit = tocalc.getText() + nine.getLabel();
						 tocalc.setText(digit);
					}
				});
				
				Button zero = new Button("0");
				zero.setForeground(Color.BLACK);
				zero.setFont(new Font("Dialog", Font.BOLD, 35));
				zero.setBounds(103, 327, 60, 60);
				calc.getContentPane().add(zero);
				zero.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String digit = tocalc.getText() + zero.getLabel();
						 tocalc.setText(digit);
					}
				});
				
				Button decimal = new Button(".");
				decimal.setForeground(Color.BLACK);
				decimal.setFont(new Font("Dialog", Font.BOLD, 35));
				decimal.setBounds(169, 327, 60, 60);
				calc.getContentPane().add(decimal);
				decimal.addActionListener(new ActionListener() {
				    
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        String currentText = tocalc.getText();
				        // Check if there's already a decimal point in the text
				        if (!currentText.contains(".")) {
				            // If not, append the decimal point
				            tocalc.setText(currentText + ".");
				        }
				    }
				});
				
				Button equal = new Button("=");
				equal.setForeground(new Color(0, 100, 0));
				equal.setFont(new Font("Dialog", Font.BOLD, 20));
				equal.setBounds(210, 75, 45, 33);
				calc.getContentPane().add(equal);
				equal.addActionListener(new ActionListener() {
				    
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        try {
				            num2 = Double.parseDouble(tocalc.getText());
				            
				            if(operator == "+") {
				                res = num1 + num2;
				                tocalc.setText(String.valueOf(res));
				            } else if(operator == "-") {
				                res = num1 - num2;
				                tocalc.setText(String.valueOf(res));
				            } else if(operator == "*") {
				                res = num1 * num2;
				                tocalc.setText(String.valueOf(res));
				            } else if(operator == "/") {
				                if(num2 != 0) {
				                    res = num1 / num2;
				                    tocalc.setText(String.valueOf(res));
				                } else {
				                    // Handling division by zero error
				                    JOptionPane.showMessageDialog(null, "Error: Division by zero", "Error", JOptionPane.ERROR_MESSAGE);
				                }
				            }
				        } catch (NumberFormatException ex) {
				            // Handling invalid input format
				            JOptionPane.showMessageDialog(null, "Error: Invalid input format", "Error", JOptionPane.ERROR_MESSAGE);
				        }
				    }
				});
				
				Button clear = new Button("AC");
				clear.setForeground(new Color(0, 100, 0));
				clear.setFont(new Font("Dialog", Font.BOLD, 20));
				clear.setBounds(273, 75, 45, 33);
				calc.getContentPane().add(clear);
                clear.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						 tocalc.setText("");
						 String fn, sn;
						 fn = String.valueOf(num1);
						 sn = String.valueOf(num2);
						 fn = "";
						 sn = "";
					}
				});
                
                prev = new JButton("Previous");
                prev.setFont(new Font("Dialog", Font.BOLD, 18));
        		prev.setBounds(10, 420, 124, 30);
        		prev.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        calc.dispose(); // Close the current frame
                        cmf.setVisible(true); // Show the previous frame
                    }

                });
                calc.getContentPane().add(prev);

	}

}
