package org.phin.deskcalc.util;

import javax.swing.UIManager;

public class LAFHandler {

	public static void setNativeLookAndFeel() {
		 try {
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 } catch(Exception e) {
			 System.out.println("Error setting native LAF: " + e);
		 } 
	 }

}
