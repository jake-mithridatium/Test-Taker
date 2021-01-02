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



public class SAAnswer extends Answer {
	
	public SAAnswer() {
		super();
	}
	
	
	public SAAnswer(String t) {
		super();
		text = t;
	}
	
	
	public SAAnswer(Scanner s) {
		text = s.nextLine();
	}
	
	
	public void print() {
		System.out.println(text);
	}
	
	
	public double getCredit(Answer rightAnswer) {
		if (rightAnswer.getText().equalsIgnoreCase(text)) { // compares correct answer with student's answer, not case-sensitive
			return 1.0;
		}
		
		else {
			return 0.0;
		}
	}
	
	
	public String getText() {
		return text;
	}
	
	
	public void save(PrintWriter pw) {
		pw.println(text);
	}
	
}
