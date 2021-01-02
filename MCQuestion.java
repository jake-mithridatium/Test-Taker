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



public abstract class MCQuestion extends Question {

	public ArrayList<MCAnswer> answers;
	
	
	
	
	public MCQuestion(String t, double mv) {
		super(t, mv);
		answers = new ArrayList<MCAnswer>();
	}
	
	
	public MCQuestion(Scanner s) {
		super(s);
		answers = new ArrayList<MCAnswer>();
	}
	
	
	public void print() {
		System.out.println(text);
		
		char letter = 'A';
		
		for (int i = 0; (i < answers.size()) && (i < 26); i++) // stops at the letter 'Z' if needed
		{
			System.out.print((char)(letter+i) + ") ");
			
			if (studentAnswer != null && answers.get(i).getText().equalsIgnoreCase(studentAnswer.getText()))
			{
				System.out.print("* ");
			}
			
			answers.get(i).print();
		}
	}
	
	
	public void addAnswer(MCAnswer a) { // adds an answer to the ArrayList
		answers.add(a);
	}
	
	
	public void reorderAnswers() { // randomly reorders the answer choices of a question
		Collections.shuffle(answers);
	}
	
	
	public void setRightAnswer(Answer a) {
		rightAnswer = a;
	}
	
	
	public double getValue(MCAnswer a) {
		double earnedValue = 0.0;
		
		for (int i = 0; i < answers.size(); i++) // iterates thru entire ArrayList of answers
		{
			earnedValue += answers.get(i).getCredit(a); // adds points of each selected answer in 'answers'
		}
		
		return maxValue * earnedValue;
	}
	
	
	public void save(PrintWriter pw) {
	}
	
	
}
