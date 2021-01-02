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



public class NumAnswer extends Answer {

	protected double numbertext;
	
	
	
	public NumAnswer() {
		super();
		text = Double.toString(numbertext);
	}
    
	
	public NumAnswer(double n) {
		super();
		numbertext = n;
		text = Double.toString(numbertext);
	}
	
	
	public NumAnswer(String t) {
		super();
		numbertext = Double.parseDouble(t);
		text = Double.toString(numbertext);
	}

    
    public NumAnswer(Scanner s) {
        numbertext = Double.parseDouble(s.nextLine());
        text = Double.toString(numbertext);
    }

    
	public void print() {
		System.out.println(text);
	}

	
	public double getCredit(Answer rightAnswer) { // determines whether the question is right or wrong (no tolerance)
		if (rightAnswer.getText().equalsIgnoreCase(text)) { // compares correct answer with student's answer
			return 1.0;
		}
		
		else {
			return 0.0;
		}
	}
	

	public double getCredit(Answer rightAnswer, double tolerance) { // determines whether the question is right or wrong (tolerance)
		double rightnumbertext = Double.parseDouble(rightAnswer.getText());
		double lowerBound = rightnumbertext - tolerance;
		double upperBound = rightnumbertext + tolerance;
		
		if ((numbertext >= lowerBound) && (numbertext <= upperBound)) { // compares correct answer with student's answer within a tolerance
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
