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



public class MCSAQuestion extends MCQuestion {
	
	public MCSAQuestion(String t, double mv) {
		super(t, mv);
	}
	
	
	public MCSAQuestion(Scanner s) {
		super(s);
		int answersSize = Integer.parseInt(s.nextLine());
		
		int i = 0;
		while (i < answersSize) {
			answers.add(new MCSAAnswer(s));
			i++;
		}
	}
	
	
	public Answer getNewAnswer() {
		Answer newAnswer = new MCSAAnswer("", 0.0);
		return newAnswer;
	}
	
	
	public Answer getNewAnswer(String t, double cis) {
		Answer newAnswer = new MCSAAnswer(t, cis);
		return newAnswer;
	}
			
		
	public void getAnswerFromStudent() {
		print();

		Scanner input = ScannerFactory.getKeyboardScanner(); // reads from System.in
		String t = input.nextLine();

		char letter = Character.toUpperCase(t.charAt(0));
		int choice = letter - 'A';
		
		if (choice < 0 || choice >= answers.size())
		{
			return;
		}
		
		studentAnswer = answers.get(choice);
		
		System.out.println();
	}
	
	
	public boolean getAnswerFromStudent(Scanner input) {
		print();

		// Scanner input = ScannerFactory.getKeyboardScanner(); // reads from System.in
		String t = input.nextLine();
		
		if (t.equalsIgnoreCase("skip"))
		{
			return true;
		}

		char letter = Character.toUpperCase(t.charAt(0));
		int choice = letter - 'A';
		
		if (choice < 0 || choice >= answers.size())
		{
			return false;
		}
		
		studentAnswer = answers.get(choice);
		
		System.out.println();
		return false;
	}
	
	
	public double getValue() {
		return super.getValue((MCAnswer) studentAnswer);
	}

	
	public void save(PrintWriter pw) {
		pw.println(maxValue);
		pw.println(text);
		pw.println(answers.size());

		for (int i = 0; i < answers.size(); i++)
		{
			answers.get(i).save(pw);
		}
	}
	
}
