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



public class SAQuestion extends Question {

	public SAQuestion(String t, double mv) {
		super(t, mv);
	}
	
	
	public SAQuestion(Scanner s) {
		super(s);
		rightAnswer = new SAAnswer(s);
	}
	
	
	public Answer getNewAnswer() {
		return new SAAnswer("");
	}
	
	
	public Answer getNewAnswer(String t) {
		return new SAAnswer(t);
	}
	
	
	public void getAnswerFromStudent() {
		Scanner input = ScannerFactory.getKeyboardScanner(); // reads from System.in
		print();
		studentAnswer = new SAAnswer(input.nextLine()); // scans the next token input as a string
		System.out.println();
	}
	
	
	public boolean getAnswerFromStudent(Scanner input) {
		print();
		
		String t = input.nextLine();
		
		if (t.equalsIgnoreCase("skip"))
		{
			return true;
		}
		
		studentAnswer = new SAAnswer(t); // scans the next token input as a string
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
		return (studentAnswer == null) ? 0.0 : (maxValue * studentAnswer.getCredit(rightAnswer));
	}
	
	
	public void save(PrintWriter pw) {
		pw.println(maxValue);
		pw.println(text);
		rightAnswer.save(pw);
	}
	
}
