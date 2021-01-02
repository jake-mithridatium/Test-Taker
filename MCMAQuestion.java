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



public class MCMAQuestion extends MCQuestion {
	
	protected double baseCredit;
	protected ArrayList<MCAnswer> studentAnswers;
	
	
	
	public MCMAQuestion(String t, double mv, double bc) {
		super(t, mv);
		baseCredit = bc;
		studentAnswers = new ArrayList<MCAnswer>();
	}
	
	
	public MCMAQuestion(Scanner s) {
		super(s);
		studentAnswers = new ArrayList<MCAnswer>();
		baseCredit = Double.parseDouble(s.nextLine());
		int answersSize = Integer.parseInt(s.nextLine());
		
		int i = 0;
		while (i < answersSize) {
			answers.add(new MCSAAnswer(s));
			i++;
		}
	}
	
	
	public void print() {
		System.out.println(text);
		
		char letter = 'A';
		
		for (int i = 0; (i < answers.size()) && (i < 26); i++) // stops at the letter 'Z' if needed
		{
			System.out.print((char)(letter+i) + ") ");
			
			for (int j = 0; (j < studentAnswers.size()); j++)
			{	
				if (studentAnswers.get(j) != null && answers.get(i).getText().equalsIgnoreCase(studentAnswers.get(j).getText()))
				{
					System.out.print("* ");
				}
			}
			
			answers.get(i).print();
		}
	}
	
	
	public Answer getNewAnswer() {
		Answer newAnswer = new MCMAAnswer("", 0.0);
		return newAnswer;
	}
	
	
	public Answer getNewAnswer(String t, double cis) {
		Answer newAnswer = new MCMAAnswer(t, cis);
		return newAnswer;
	}
			
		
	public void getAnswerFromStudent() {
		System.out.println("(Type letters to choose answers)");
		System.out.println("(Separate multiple answers with spaces)");
		print();

		Scanner input = ScannerFactory.getKeyboardScanner(); // reads from System.in
		String t = input.nextLine();
		String[] t2 = t.split(" ");
		
		for (int i = 0; i < t2.length; i++)
		{
			char letter = Character.toUpperCase(t2[i].charAt(0));
			int choice = letter - 'A';
			
			if (choice < 0 || choice >= answers.size())
			{
				return;
			}
			
			studentAnswers.add(answers.get(choice));
		}
		
		System.out.println();
	}
	
	
	public boolean getAnswerFromStudent(Scanner input) {
		System.out.println("(Type letters to choose answers)");
		System.out.println("(Separate multiple answers with spaces)");
		print();

		// Scanner input = ScannerFactory.getKeyboardScanner(); // reads from System.in
		String t = input.nextLine();
		
		if (t.equalsIgnoreCase("skip"))
		{
			return true;
		}
		
		String[] t2 = t.split(" ");
		
		for (int i = 0; i < t2.length; i++)
		{
			char letter = Character.toUpperCase(t2[i].charAt(0));
			int choice = letter - 'A';
			
			if (choice < 0 || choice >= answers.size())
			{
				return false;
			}
			
			studentAnswers.add(answers.get(choice));
		}
		
		System.out.println();
		return false;
	}
	
	
	public double getValue() {
		double earnedValue = 0.0;
		
		for (int i = 0; i < studentAnswers.size(); i++) // iterates thru entire ArrayList of answers
		{
			earnedValue += super.getValue((MCAnswer) studentAnswers.get(i)); // adds points of each selected answer in 'answers'
		}
		
		return (maxValue * baseCredit) + earnedValue;
	}

	
	public void save(PrintWriter pw) {
		pw.println(maxValue);
		pw.println(text);
		pw.println(baseCredit);
		pw.println(answers.size());

		for (int i = 0; i < answers.size(); i++)
		{
			answers.get(i).save(pw);
		}
	}
	
	
	public void saveStudentAnswers(PrintWriter pw) {
		pw.println(studentAnswers.size());
		
		for (int i = 0; i < studentAnswers.size(); i++)
		{
			pw.println(studentAnswers.get(i).getText());
		}
	}
	
	
	public void restoreStudentAnswers(Scanner s) {
		s.nextLine();

		int numSelectedAnswers = Integer.parseInt(s.nextLine());
		
		for (int i = 0; i < numSelectedAnswers; i++) {
			studentAnswers.add(new MCMAAnswer(s.nextLine(), 0));
		}
	}
	
}