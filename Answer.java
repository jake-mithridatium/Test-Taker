/*
 * GROUP 48
 * Mohammad Abuosbie (mabuos2)
 * Jacob McKibben(jmckib2)
 * Jacob Zaworski(jzawor2)
 * 
 * Term Project (Part 4)
 */


// import java.util.*;
import java.io.*;



public abstract class Answer {
	
	protected String text;
	
	
	
	protected Answer() {
		return;
	}
	
	
	public abstract void print();

	
	public abstract double getCredit(Answer rightAnswer);

	
	public String getText() {
		return text;
	}
	
	
	public abstract void save(PrintWriter pw);
	
}


