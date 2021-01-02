/*
 * GROUP 48
 * Mohammad Abuosbie (mabuos2)
 * Jacob McKibben(jmckib2)
 * Jacob Zaworski(jzawor2)
 * 
 * Term Project (Part 4)
 */


import java.util.*;
import java.io.*;



public abstract class MCAnswer extends Answer {

	protected double creditIfSelected;
	
	
	
	
	protected MCAnswer(String t, double cis) {
		text = t;
		creditIfSelected = cis;
	}
	
	
	public MCAnswer(Scanner s) {
		creditIfSelected = s.nextDouble();
		s.skip(" ");
		text = s.nextLine();
	}
	
	public void print() {
		System.out.println(text);
	}
	
	
	public double getCredit(Answer rightAnswer) {
		if (rightAnswer.getText().equalsIgnoreCase(text)) {
			return creditIfSelected;
		}
			
		else {
			return 0.0;
		}
	}
	
	
	public String getText() {
		return text;
	}
	
	
	public void save(PrintWriter pw) {
		pw.println(creditIfSelected + " " + text);
	}
	
}
