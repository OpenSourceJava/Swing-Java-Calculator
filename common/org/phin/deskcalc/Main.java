package org.phin.deskcalc;

import javax.swing.SwingUtilities;

import org.phin.deskcalc.frame.BasicCalculatorFrame;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BasicCalculatorFrame frame = new BasicCalculatorFrame();
				frame.setVisible(true);
			}
		});
	}

}
