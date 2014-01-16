package org.phin.deskcalc.frame;

import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.phin.deskcalc.util.LAFHandler;

public class BasicCalculatorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField operatorField;
	private JTextField textField;
	private JButton btnDelete;
	private JButton btnClear;
	private JButton btnExit;
	private JButton btnSqrt;
	private JButton btnPi;
	private JButton btnSqrd;
	private JButton btnPosNeg;
	private JButton btnDecimal;
	private JButton btnEquals;
	
	// arithmetic components
	private JButton btnDivide;
	private JButton btnMultiply;
	private JButton btnAdd;
	private JButton btnModulis;
	private JButton btnMinus;
	
	// key dispatcher
	KeyboardFocusManager manager;
	
	// number variables 
	private JButton btn0;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	
	// values
	private double value1;
	private double value2;
	private String operator;
	
	public BasicCalculatorFrame() {
		this.createGUI();
	}
	
	private void createGUI() {
		
		LAFHandler.setNativeLookAndFeel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 300, 340);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		this.setTitle("Desktop Calculator");
		this.setResizable(false);
		this.contentPane.setBackground(new Color(64, 64, 64));
		this.contentPane.setLayout(null);
		
		// key manager
		this.manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		this.manager.addKeyEventDispatcher(new dispatcher());
		
		this.operatorField = new JTextField();
		this.operatorField.setEditable(false);
		this.operatorField.setBounds(10, 11, 274, 20);
		this.operatorField.setColumns(10);
		this.contentPane.add(this.operatorField);
		
		this.textField = new JTextField();
		this.textField.setEditable(false);
		this.textField.setBounds(10, 42, 274, 20);
		this.textField.setColumns(10);
		this.contentPane.add(textField);
		
		btnClear = new JButton("clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				operatorField.setText("");
				textField.setText("");
				value1 = 0;
				value2 = 0;
				operator = "";
			}
		});
		btnClear.setBounds(10, 73, 89, 23);
		contentPane.add(btnClear);
		
		btnDelete = new JButton("delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String text = textField.getText();
				
				if (text.length() > 0) {
					String sub = text.substring(0, text.length() - 1);
					textField.setText(sub);
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		btnDelete.setBounds(109, 73, 89, 23);
		contentPane.add(btnDelete);
		
		btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				System.exit(1);
			}
		});
		btnExit.setBounds(208, 73, 76, 23);
		contentPane.add(btnExit);
		
		btnPi = new JButton("PI");
		btnPi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textField.getText().equals(String.valueOf(Math.PI))) {
					textField.setText(String.valueOf(Math.PI));
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		btnPi.setBounds(10, 107, 43, 23);
		contentPane.add(btnPi);
		
		btnSqrt = new JButton("sqrt");
		btnSqrt.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textField.getText().equals("")) {
					if ((value1 == 0) && (value2 == 0)) {
						String text = textField.getText();
						double val = Double.parseDouble(text);
						val = Math.sqrt(val);
						operatorField.setText("square root of " + Double.parseDouble(text) + " = " + val);		
						textField.setText("");
					} else {
						String text = textField.getText();
						double val = Double.parseDouble(text);
						val = Math.sqrt(val);
						textField.setText(String.valueOf(val));	
					}
				}
			}
		});
		btnSqrt.setBounds(63, 107, 61, 23);
		contentPane.add(btnSqrt);
		
		btnSqrd = new JButton("sqrd");
		btnSqrd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textField.getText().equals("")) {
					if ((value1 == 0) && (value2 == 0)) {
						String text = textField.getText();
						double val = Double.parseDouble(text);
						val = Math.pow(val, 2);
						operatorField.setText(Double.parseDouble(text) + " squared " + " = " + val);		
						textField.setText("");
					} else {
						String text = textField.getText();
						double val = Double.parseDouble(text);
						val = Math.pow(val, 2);
						textField.setText(String.valueOf(val));	
					}
				}
			}
		});
		btnSqrd.setBounds(134, 107, 61, 23);
		contentPane.add(btnSqrd);
		
		btnModulis = new JButton("modulis");
		btnModulis.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textField.getText().equals("")) {
					operator = "%";
					if (value1 == 0) {
						String text = textField.getText();
						value1 = Double.parseDouble(text);
						operatorField.setText(value1 + " % ");
						textField.setText("");
					} else if (value2 == 0) {
						String text = textField.getText();
						value2 = Double.parseDouble(text);
						value1 %= value2;
						operatorField.setText(value1 + " % ");
						textField.setText("");
					}
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		btnModulis.setBounds(208, 107, 76, 23);
		contentPane.add(btnModulis);
		
		btn7 = new JButton("7");
		this.btn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText(textField.getText() + "7");
			}
		});
		btn7.setBounds(10, 141, 61, 23);
		contentPane.add(btn7);
		
		btn8 = new JButton("8");
		this.btn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText(textField.getText() + "8");
			}
		});
		btn8.setBounds(81, 141, 61, 23);
		contentPane.add(btn8);
		
		btn9 = new JButton("9");
		this.btn9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText(textField.getText() + "9");
			}
		});
		btn9.setBounds(152, 141, 61, 23);
		contentPane.add(btn9);
		
		btnPosNeg = new JButton("+/-");
		btnPosNeg.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (textField.getText().startsWith("-")) {
					String text = textField.getText();
					String sub = text.substring(1, text.length());
					textField.setText(sub);
				} else if (!textField.getText().startsWith("-")) {
					String text = textField.getText();
					String concat = "-" + text;
					textField.setText(concat);
				}
			}
		});
		btnPosNeg.setBounds(223, 141, 61, 23);
		contentPane.add(btnPosNeg);
		
		btn4 = new JButton("4");
		this.btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText(textField.getText() + "4");
			}
		});
		btn4.setBounds(10, 175, 61, 23);
		contentPane.add(btn4);
		
		btn5 = new JButton("5");
		this.btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText(textField.getText() + "5");
			}
		});
		btn5.setBounds(81, 175, 61, 23);
		contentPane.add(btn5);
		
		btn6 = new JButton("6");
		this.btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText(textField.getText() + "6");
			}
		});
		btn6.setBounds(152, 175, 61, 23);
		contentPane.add(btn6);
		
		btnAdd = new JButton("+");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textField.getText().equals("")) {
					operator = "+";
					if (value1 == 0) {
						String text = textField.getText();
						value1 = Double.parseDouble(text);
						operatorField.setText(value1 + " + ");
						textField.setText("");
					} else if (value2 == 0) {
						String text = textField.getText();
						value2 = Double.parseDouble(text);
						value1 += value2;
						operatorField.setText(value1 + " + ");
						textField.setText("");
						value2 = 0;
					} else {
						Toolkit.getDefaultToolkit().beep();
					}	
				}
			}
		});
		btnAdd.setBounds(223, 175, 61, 23);
		contentPane.add(btnAdd);
		
		btn1 = new JButton("1");
		this.btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText(textField.getText() + "1");
			}
		});
		btn1.setBounds(10, 209, 61, 23);
		contentPane.add(btn1);
		
		btn2 = new JButton("2");
		this.btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText(textField.getText() + "2");
			}
		});
		btn2.setBounds(81, 209, 61, 23);
		contentPane.add(btn2);
		
		btn3 = new JButton("3");
		this.btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText(textField.getText() + "3");
			}
		});
		btn3.setBounds(152, 209, 61, 23);
		contentPane.add(btn3);
		
		btnMinus = new JButton("-");
		btnMinus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textField.getText().equals("")) {
					operator = "-";
					if (value1 == 0) {
						String text = textField.getText();
						value1 = Double.parseDouble(text);
						operatorField.setText(value1 + " - ");
						textField.setText("");
					} else if (value2 == 0) {
						String text = textField.getText();
						value2 = Double.parseDouble(text);
						value1 -= value2;
						operatorField.setText(value1 + " - ");
						textField.setText("");
						value2 = 0;
					}
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		btnMinus.setBounds(223, 209, 61, 23);
		contentPane.add(btnMinus);
		
		btn0 = new JButton("0");
		this.btn0.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textField.setText(textField.getText() + "0");
			}
		});
		btn0.setBounds(10, 243, 132, 23);
		contentPane.add(btn0);
		
		btnDecimal = new JButton(".");
		btnDecimal.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textField.getText().contains(".")) {
					textField.setText(textField.getText() + ".");
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		btnDecimal.setBounds(152, 243, 61, 23);
		contentPane.add(btnDecimal);
		
		btnEquals = new JButton("=");
		btnEquals.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textField.getText().equals("")) {
					if (operator.equals("+")) {
						String text = textField.getText();
						value2 = Double.parseDouble(text);
						double answer = value1 + value2;
						operatorField.setText(value1 + " + " + value2 + " = " + answer);
						textField.setText("");
						value1 += value2;
						value2 = 0;
					} else if (operator.equals("-")) {
						String text = textField.getText();
						value2 = Double.parseDouble(text);
						double answer = value1 - value2;
						operatorField.setText(value1 + " - " + value2 + " = " + answer);
						textField.setText("");
						value1 -= value2;
						value2 = 0;
					} else if (operator.equals("*")) {
						String text = textField.getText();
						value2 = Double.parseDouble(text);
						double answer = value1 * value2;
						operatorField.setText(value1 + " * " + value2 + " = " + answer);
						textField.setText("");
						value1 *= value2;
						value2 = 0;
					} else if (operator.equals("/")) {
						String text = textField.getText();
						value2 = Double.parseDouble(text);
						double answer = value1 / value2;
						operatorField.setText(value1 + " / " + value2 + " = " + answer);
						textField.setText("");
						value1 /= value2;
						value2 = 0;
					}
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		btnEquals.setBounds(10, 277, 203, 23);
		contentPane.add(btnEquals);
		
		btnMultiply = new JButton("*");
		btnMultiply.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textField.getText().equals("")) {
					operator = "*";
					if (value1 == 0) {
						String text = textField.getText();
						value1 = Double.parseDouble(text);
						operatorField.setText(value1 + " * ");
						textField.setText("");
					} else if (value2 == 0) {
						String text = textField.getText();
						value2 = Double.parseDouble(text);
						value1 *= value2;
						operatorField.setText(value1 + " * ");
						textField.setText("");
						value2 = 0;
					}
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		btnMultiply.setBounds(223, 243, 61, 23);
		contentPane.add(btnMultiply);
		
		btnDivide = new JButton("/");
		this.btnDivide.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (!textField.getText().equals("")) {
					operator = "/";
					if (value1 == 0) {
						String text = textField.getText();
						value1 = Double.parseDouble(text);
						operatorField.setText(value1 + " / ");
						textField.setText("");
					} else if (value2 == 0) {
						String text = textField.getText();
						value2 = Double.parseDouble(text);
						value1 /= value2;
						operatorField.setText(value1 + " / ");
						textField.setText("");
						value2 = 0;
					}
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});
		btnDivide.setBounds(223, 277, 61, 23);
		contentPane.add(btnDivide);
		
		
	}

	private class dispatcher implements KeyEventDispatcher {
		
		public boolean dispatchKeyEvent(KeyEvent e) {
			if (e.getID() == KeyEvent.KEY_PRESSED) {
				if (e.getKeyCode() == KeyEvent.VK_0) {
					textField.setText(textField.getText() + "0");
				} else if (e.getKeyCode() == KeyEvent.VK_1) {
					textField.setText(textField.getText() + "1");
				} else if (e.getKeyCode() == KeyEvent.VK_2) {
					textField.setText(textField.getText() + "2");
				} else if (e.getKeyCode() == KeyEvent.VK_3) {
					textField.setText(textField.getText() + "3");
				} else if (e.getKeyCode() == KeyEvent.VK_4) {
					textField.setText(textField.getText() + "4");
				} else if (e.getKeyCode() == KeyEvent.VK_5) {
					textField.setText(textField.getText() + "5");
				} else if (e.getKeyCode() == KeyEvent.VK_6) {
					textField.setText(textField.getText() + "6");
				} else if (e.getKeyCode() == KeyEvent.VK_7) {
					textField.setText(textField.getText() + "7");
				} else if (e.getKeyCode() == KeyEvent.VK_8) {
					textField.setText(textField.getText() + "8");
				} else if (e.getKeyCode() == KeyEvent.VK_9) {
					textField.setText(textField.getText() + "9");
				} else if (e.getKeyCode() == KeyEvent.VK_PERIOD) {
					textField.setText(textField.getText() + ".");
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
					textField.setText(textField.getText() + "0");
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
					textField.setText(textField.getText() + "1");
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
					textField.setText(textField.getText() + "2");
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
					textField.setText(textField.getText() + "3");
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
					textField.setText(textField.getText() + "4");
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
					textField.setText(textField.getText() + "5");
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
					textField.setText(textField.getText() + "6");
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
					textField.setText(textField.getText() + "7");
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
					textField.setText(textField.getText() + "8");
				} else if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
					textField.setText(textField.getText() + "9");
				} else if (e.getKeyCode() == KeyEvent.VK_DECIMAL) {
					textField.setText(textField.getText() + ".");
				} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					if (!textField.isFocusOwner()) {
						String text = textField.getText();
						
						if (text.length() > 0) {
							String sub = text.substring(0, text.length() - 1);
							textField.setText(sub);
						} else {
							Toolkit.getDefaultToolkit().beep();
						}
					}
				} else if (e.getKeyCode() == KeyEvent.VK_ADD) {
					if (!textField.getText().equals("")) {
						operator = "+";
						if (value1 == 0) {
							String text = textField.getText();
							value1 = Double.parseDouble(text);
							operatorField.setText(value1 + " + ");
							textField.setText("");
						} else if (value2 == 0) {
							String text = textField.getText();
							value2 = Double.parseDouble(text);
							value1 += value2;
							operatorField.setText(value1 + " + ");
							textField.setText("");
							value2 = 0;
						} else {
							Toolkit.getDefaultToolkit().beep();
						}	
					}
				} else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
					if (!textField.getText().equals("")) {
						operator = "-";
						if (value1 == 0) {
							String text = textField.getText();
							value1 = Double.parseDouble(text);
							operatorField.setText(value1 + " - ");
							textField.setText("");
						} else if (value2 == 0) {
							String text = textField.getText();
							value2 = Double.parseDouble(text);
							value1 -= value2;
							operatorField.setText(value1 + " - ");
							textField.setText("");
							value2 = 0;
						}
					} else {
						Toolkit.getDefaultToolkit().beep();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
					if (!textField.getText().equals("")) {
						operator = "*";
						if (value1 == 0) {
							String text = textField.getText();
							value1 = Double.parseDouble(text);
							operatorField.setText(value1 + " * ");
							textField.setText("");
						} else if (value2 == 0) {
							String text = textField.getText();
							value2 = Double.parseDouble(text);
							value1 *= value2;
							operatorField.setText(value1 + " * ");
							textField.setText("");
							value2 = 0;
						}
					} else {
						Toolkit.getDefaultToolkit().beep();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_DIVIDE) {
					
				} else if (e.getKeyCode() == KeyEvent.VK_EQUALS){
					if (!textField.getText().equals("")) {
						if (operator.equals("+")) {
							String text = textField.getText();
							value2 = Double.parseDouble(text);
							double answer = value1 + value2;
							operatorField.setText(value1 + " + " + value2 + " = " + answer);
							textField.setText("");
							value1 += value2;
							value2 = 0;
						} else if (operator.equals("-")) {
							String text = textField.getText();
							value2 = Double.parseDouble(text);
							double answer = value1 - value2;
							operatorField.setText(value1 + " - " + value2 + " = " + answer);
							textField.setText("");
							value1 -= value2;
							value2 = 0;
						} else if (operator.equals("*")) {
							String text = textField.getText();
							value2 = Double.parseDouble(text);
							double answer = value1 * value2;
							operatorField.setText(value1 + " * " + value2 + " = " + answer);
							textField.setText("");
							value1 *= value2;
							value2 = 0;
						} else if (operator.equals("/")) {
							String text = textField.getText();
							value2 = Double.parseDouble(text);
							double answer = value1 / value2;
							operatorField.setText(value1 + " / " + value2 + " = " + answer);
							textField.setText("");
							value1 /= value2;
							value2 = 0;
						}
					} else {
						Toolkit.getDefaultToolkit().beep();
					}
				} else {
					Toolkit.getDefaultToolkit().beep();
				}
			}
			
			return false;
		}		
	}
}
