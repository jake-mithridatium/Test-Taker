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
import java.time.LocalDateTime;
import java.time.format.*;



public class Exam {

	public ArrayList<Question> questions = new ArrayList<Question>();
	private String text;
	private String timestamp;
	public String fileName;
	public String studentName;
	
	
	
	public Exam(String t) { // Exam constructor with a string parameter to initialize 'title'
		text = t;
		return;
	}
	
	
	public Exam(Scanner s) {
		text = s.nextLine();
		timestamp = s.nextLine();
		
		String questionType = s.nextLine();
		
		while (s.hasNextLine()) {
			questionType = s.nextLine();
			
			if (questionType.equals("SAQuestion"))
			{
				questions.add(new SAQuestion(s));
			}
			
			else if (questionType.equals("MCSAQuestion"))
			{
				questions.add(new MCSAQuestion(s));
			}
			
			else if (questionType.equals("MCMAQuestion"))
			{
				questions.add(new MCMAQuestion(s));
			}
			
			else if (questionType.equals("NumQuestion"))
			{
				questions.add(new NumQuestion(s));
			}
		}
	}
	

	public void print() { // prints out an entire exam (includes heading, questions, and answer choices)
		System.out.println(text + "\n");

		for (int i = 0; i < questions.size(); i++)
		{
			if (questions.get(i) instanceof SAQuestion)
			{
				System.out.println("SAQuestion");
			}
			
			else if (questions.get(i) instanceof MCSAQuestion)
			{
				System.out.println("MCSAQuestion");
			}
			
			else if (questions.get(i) instanceof MCMAQuestion)
			{
				System.out.println("MCMAQuestion");
			}
			
			else if (questions.get(i) instanceof NumQuestion)
			{
				System.out.println("NumQuestion");
			}
			
			System.out.print((i+1) + ". ");
			questions.get(i).print();
			System.out.println();
		}
		
		// reportQuestionValues();
	}
	
	
	public void addQuestion(Question q) { // adds a question to the ArrayList
		questions.add(q);
	}
	
	public void removeQuestion(int position) {
		if (position == -1)
		{
			while (questions.isEmpty() == false)
			{
				questions.remove(0);
			}
		}
		
		else if (position >= 0 && position < questions.size())
		{
			questions.remove(position);
		}
	}
	
	
	public void reorderQuestions() { // randomly reorders the questions on the exam
		Collections.shuffle(questions);
	}

	
	public void reorderMCAnswers(int position) {
		if (position == -1)
		{
			for (int i = 0; i < questions.size(); i++)
			{
				if (questions.get(i) instanceof MCQuestion)
				{
					MCQuestion mcq = (MCQuestion) questions.get(i);
					mcq.reorderAnswers();
				}
			}
		}
		
		else if (position >= 0 && position < questions.size())
		{
			if (questions.get(position) instanceof MCQuestion)
			{
				MCQuestion mcq = (MCQuestion) questions.get(position);
				mcq.reorderAnswers();
			}
		}
	}
	
	
	public void getInfoFromStudent() {
		Scanner keyboardinput = ScannerFactory.getKeyboardScanner(); // reads from System.in
		System.out.println("What is your name?");
		studentName = keyboardinput.nextLine();
		
		System.out.println("What is the filename of the exam you are answering?");
		fileName = keyboardinput.nextLine();
		
		System.out.println();
	}
	
	
	public void getAnswerFromStudent(int position) {
		if (position >= 0 && position < questions.size())
		{
			questions.get(position).getAnswerFromStudent();
		}
		
		else if (position < 0)
		{
			for (int i = 0 ; i < questions.size(); i++)
			{
				questions.get(i).getAnswerFromStudent();
			}
		}
	}
	
	
	public ArrayList<Integer> getAnswerFromStudent(Scanner input) {
		//Scanner input = ScannerFactory.getKeyboardScanner(); // reads from System.in
		ArrayList<Integer> skipped = new ArrayList<Integer>();
		boolean isSkipped = false;
		
		for (int i = 0 ; i < questions.size(); i++)
		{
			isSkipped = questions.get(i).getAnswerFromStudent(input);
			
			if (isSkipped == true)
			{
				skipped.add(i);
			}
		}

		return skipped;
	}
	
	
	public double getValue() { // returns the total score of the exam
		double earnedValue = 0.0;
		
		Iterator<Question> itr = questions.iterator();
		
		while(itr.hasNext()) // iterates thru entire ArrayList of answers
		{
			earnedValue += itr.next().getValue();
		}
		
		return earnedValue;
	}
	
	
	public void reportQuestionValues() {
		System.out.println("\n" + "Question    |    Score");
		System.out.println("------------+---------");

		for (int i = 0; i < questions.size(); i++)
		{
			System.out.println("       " + (i+1) + "    |    " + questions.get(i).getValue());
		}
		
		System.out.println("------------+---------");
		System.out.println("   Total    |    " + getValue());
	}
	
	
	public void save(PrintWriter pw) {
		pw.println(text);
		timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		pw.println(timestamp + "\n");

		for (int i = 0; i < questions.size(); i++)
		{
			if (questions.get(i) instanceof SAQuestion)
			{
				pw.println("SAQuestion");
			}
			
			else if (questions.get(i) instanceof MCSAQuestion)
			{
				pw.println("MCSAQuestion");
			}
			
			else if (questions.get(i) instanceof MCMAQuestion)
			{
				pw.println("MCMAQuestion");
			}

			else if (questions.get(i) instanceof NumQuestion)
			{
				pw.println("NumQuestion");
			}
			
			questions.get(i).save(pw);
			pw.println();
		}
	}
	
	
	public void saveStudentAnswers(PrintWriter pw) {
		pw.println(studentName);
		pw.println(fileName);
		timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		pw.println(timestamp + "\n");

		for (int i = 0; i < questions.size(); i++)
		{
			if (questions.get(i) instanceof SAQuestion)
			{
				pw.println("SAAnswer");
			}
			
			else if (questions.get(i) instanceof MCSAQuestion)
			{
				pw.println("MCSAAnswer");
			}
			
			else if (questions.get(i) instanceof MCMAQuestion)
			{
				pw.println("MCMAAnswer");
			}
			
			else if (questions.get(i) instanceof NumQuestion)
			{
				pw.println("NumAnswer");
			}
			
			questions.get(i).saveStudentAnswers(pw);
			pw.println();
		}
	}
	
	
	public void restoreStudentAnswers(Scanner s) {
		studentName = s.nextLine();
		fileName = s.nextLine();
		timestamp = s.nextLine();
		
		for (int i = 0; i < questions.size(); i++)
		{
			s.nextLine();
			questions.get(i).restoreStudentAnswers(s);
		}
	}
	
	
	public void storeQuestions(Scanner s) {
		String questionType = s.nextLine();
		questionType = s.nextLine();
		questionType = s.nextLine();
		
		while (s.hasNextLine()) {
			questionType = s.nextLine();
			
			if (questionType.equals("SAQuestion"))
			{
				questions.add(new SAQuestion(s));
			}
			
			else if (questionType.equals("MCSAQuestion"))
			{
				questions.add(new MCSAQuestion(s));
			}
			
			else if (questionType.equals("MCMAQuestion"))
			{
				questions.add(new MCMAQuestion(s));
			}
			
			else if (questionType.equals("NumQuestion"))
			{
				questions.add(new NumQuestion(s));
			}
		}
	}
	
	
    public void CSVgenerator(PrintWriter writer)
    {
        StringBuilder csv = new StringBuilder();
        csv.append("Name:" + studentName + " - " + "Total Score: " + this.getValue() +"\n");
        for (int i  = 1; i <= questions.size(); i++){
            csv.append("Question " + i+": "+questions.get(i-1).getValue()+"\n");
        }
        writer.write(csv.toString());
        System.out.println("Done.");
    }
	
}
