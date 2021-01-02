/*
 * GROUP 48
 * Mohammad Abuosbie (mabuos2)
 * Jacob McKibben (jmckib2)
 * Jacob Zaworski (jzawor2)
 * 
 * Term Project (Part 4)
 */


import java.util.*;
import java.io.*;



public class NumQuestion extends Question {

	protected double tolerance;
	
	
	
	public NumQuestion(String t, double mv, double tol) {
		super(t, mv);
		tolerance = tol;
	}


	public NumQuestion(Scanner s) {
		super(s);
		rightAnswer = new NumAnswer(s);
		tolerance = Double.parseDouble(s.nextLine());
	}
	
	
	public Answer getNewAnswer() {
		return new NumAnswer();
	}
	
	
	public Answer getNewAnswer(double n) {
		return new NumAnswer(n);
	}
	
	
	public Answer getNewAnswer(String t) {
		return new NumAnswer(t);
	}
	
	
	public void getAnswerFromStudent() {
		Scanner input = ScannerFactory.getKeyboardScanner(); // reads from System.in
		print();
		
       	String hisHerAnswer = null;

		while (true) {
	       	hisHerAnswer = input.nextLine();
	       		
	       	try {
	        		Double.parseDouble(hisHerAnswer);
	        		break;
	        }
	        catch (NumberFormatException ne) {
	            System.out.println("\tPlease enter an integer.");
	        }
		}
		
		studentAnswer = new NumAnswer(hisHerAnswer); // scans the next token input as a string
		System.out.println();
	}
	
	
	public boolean getAnswerFromStudent(Scanner input) {
		// Scanner input = ScannerFactory.getKeyboardScanner(); // reads from System.in
		print();
		
       	String hisHerAnswer = null;

		while (true) {
	       	hisHerAnswer = input.nextLine();
	       	
	       	if (hisHerAnswer.equalsIgnoreCase("skip"))
	       	{
	       		return true;
	       	}
	       		
	       	try {
	        		Double.parseDouble(hisHerAnswer);
	        		break;
	        }
	        catch (NumberFormatException ne) {
	            System.out.println("\tPlease enter an integer.");
	        }
		}
		
		studentAnswer = new NumAnswer(hisHerAnswer); // scans the next token input as a string
		System.out.println();
		return false;
	}
	
	
	public void print() {
		System.out.println(text);
		
		if (studentAnswer != null)
		{
			studentAnswer.print();
		}
	}
	
	
	public double getValue() { // returns the total value of the student's answer
		double gc = ((NumAnswer)studentAnswer).getCredit(rightAnswer, tolerance);
		return (studentAnswer == null) ? 0.0 : (maxValue * gc);
	}
	
	
	public void save(PrintWriter pw) {
		pw.println(maxValue);
		pw.println(text);
		rightAnswer.save(pw);
		pw.println(tolerance);
	}

}
