/*
 * GROUP 48
 * Mohammad Abuosbie (mabuos2)
 * Jacob McKibben (jmckib2)
 * Jacob Zaworski (jzawor2)
 * 
 * Term Project (Part 4)
 */


import java.util.*;



public class ScannerFactory {

	private static Scanner keyboardScanner = null;
	
	
	public static Scanner getKeyboardScanner() {
		if (keyboardScanner == null)
		{
			keyboardScanner = new Scanner(System.in);
		}
		
		return keyboardScanner;
	}
	
	
}
