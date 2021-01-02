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



public abstract class Question {

	protected String text;
	protected Answer rightAnswer;
	public Answer studentAnswer;
	protected double maxValue;
	
	
	
	
	protected Question(String t, double mv) { // Question constructor to initialize text & maxValue
		text = t;
		maxValue = mv;
		rightAnswer = null;
		studentAnswer = null;
	}
	
	
	public Question(Scanner s)
	{
		maxValue = Double.parseDouble(s.nextLine());
		text = s.nextLine();
	}
	
	
	public void print() { // prints out an entire question along with its answer choices
		System.out.println(text);
		return;
	}

	
	
	public void setRightAnswer(Answer a) {
		rightAnswer = a;
	}
	
	
	public abstract void getAnswerFromStudent();
	
	
	public abstract boolean getAnswerFromStudent(Scanner input);
	
	
	public abstract double getValue(); // returns the total value of the selected answers
	
	
	public abstract void save(PrintWriter pw);
	

	public void saveStudentAnswers(PrintWriter pw) {
		pw.println(studentAnswer.getText());
	}
	
	
	public void restoreStudentAnswers(Scanner s) {
		String answerType = s.nextLine();
		
		if (answerType.equals("SAAnswer"))
		{
			studentAnswer = new SAAnswer(s);
		}
		
		else if (answerType.equals("MCSAAnswer"))
		{
			studentAnswer = new MCSAAnswer(s.nextLine(), 0);
		}
		
		else if (answerType.equals("NumAnswer"))
		{
			studentAnswer = new NumAnswer(s);
		}
	}
	
	
}