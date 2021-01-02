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



public class ExamBuilder {

	public static void main(String[] args) {
		System.out.println("GROUP 48");
		System.out.println("Mohammad Abuosbie (mabuos2)");
		System.out.println("Jacob McKibben (jmckib2)");
		System.out.println("Jacob Zaworski (jzawor2)");

		System.out.print("\n\n\n\n");
		
		Scanner keyboardinput = ScannerFactory.getKeyboardScanner(); // reads from System.in
		System.out.print("Enter a title for your exam: ");
		Exam createdExam = new Exam(keyboardinput.nextLine());
		System.out.print("\n\n\n");
		
		while (true) {
			System.out.println("What action would you like to do?");
			System.out.println("A) Load questions from a saved Exam file");
			System.out.println("B) Add questions interactively");
			System.out.println("C) Remove questions interactively");
			System.out.println("D) Reorder questions and answers");
			System.out.println("E) Print the Exam");
			System.out.println("F) Save the Exam");
			System.out.println("Type 'quit' to quit");
			
			String userSelection = keyboardinput.nextLine();
			System.out.println();
			
			if (userSelection.equalsIgnoreCase("a")) // Load questions from a saved Exam file
			{	
				try {
					System.out.println("Please enter the filename of the exam you would like to load: ");
					File InputExamFile = new File(keyboardinput.nextLine());
					Scanner s = new Scanner(InputExamFile);
					createdExam.storeQuestions(s);
					s.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			else if (userSelection.equalsIgnoreCase("b")) // Add questions interactively
			{
				System.out.println("Enter the type of the question (choose from SA, MCSA, MCMA, Num): ");
				String questionType = keyboardinput.nextLine();
				System.out.println();
				
				if (questionType.equalsIgnoreCase("sa"))
				{
					System.out.println("You are now creating a Short-Answer question!\n");
					
					System.out.println("Enter the text of the question: ");
					String q1text = keyboardinput.nextLine();
					System.out.println("Enter the value of the question: ");
					double q1value = Double.parseDouble(keyboardinput.nextLine());
					Question q1 = new SAQuestion(q1text, q1value);
					
					System.out.println("Enter the answer of the question: ");
					Answer q1answer = new SAAnswer(keyboardinput.nextLine());
					q1.setRightAnswer(q1answer);
					
					createdExam.addQuestion(q1);
				}
				
				else if (questionType.equalsIgnoreCase("mcsa"))
				{
					System.out.println("You are now creating a Multiple-Choice (Single Answer) question!\n");
					
					System.out.println("Enter the text of the question: ");
					String q2text = keyboardinput.nextLine();
					System.out.println("Enter the value of the question: ");
					double q2value = Double.parseDouble(keyboardinput.nextLine());
					MCQuestion q2 = new MCSAQuestion(q2text, q2value);
					
					System.out.println("Enter the number of answer choices: ");
					int numMCAnswers = Integer.parseInt(keyboardinput.nextLine());
					
					char letter = 'A';
					for (int i = 0; i < numMCAnswers; i++)
					{
						System.out.print("Enter answer choice " + (char)(letter+i) + ": ");
						String q2answertext = keyboardinput.nextLine();
						System.out.print("Enter the value of answer choice " + (char)(letter+i) + ": ");
						double q2answervalue = Double.parseDouble(keyboardinput.nextLine());
						q2.addAnswer(new MCSAAnswer(q2answertext, q2answervalue));
					}
					
					createdExam.addQuestion(q2);
				}
				
				else if (questionType.equalsIgnoreCase("mcma"))
				{
					System.out.println("You are now creating a Multiple-Choice (Multiple Answers) question!\n");
					
					System.out.println("Enter the text of the question: ");
					String q3text = keyboardinput.nextLine();
					System.out.println("Enter the value of the question: ");
					double q3value = Integer.parseInt(keyboardinput.nextLine());
					System.out.println("Enter the base credit of the question: ");
					double q3base = Integer.parseInt(keyboardinput.nextLine());
					MCQuestion q3 = new MCMAQuestion(q3text, q3value, q3base);
					
					System.out.println("Enter the number of answer choices: ");
					int numMCAnswers = Integer.parseInt(keyboardinput.nextLine());
					
					char letter = 'A';
					for (int i = 0; i < numMCAnswers; i++)
					{
						System.out.print("Enter answer choice " + (char)(letter+i) + ": ");
						String q3answertext = keyboardinput.nextLine();
						System.out.print("Enter the value of answer choice " + (char)(letter+i) + ": ");
						double q3answervalue = Double.parseDouble(keyboardinput.nextLine());
						q3.addAnswer(new MCSAAnswer(q3answertext, q3answervalue));
					}
					
					createdExam.addQuestion(q3);
				}

				else if (questionType.equalsIgnoreCase("num"))
				{
					System.out.println("You are now creating a Number question!\n");
					
					System.out.println("Enter the text of the question: ");
					String q4text = keyboardinput.nextLine();
					System.out.println("Enter the value of the question: ");
					double q4value = Double.parseDouble(keyboardinput.nextLine());
					System.out.println("Enter the tolerance of the question: ");
					double q4tolerance = Double.parseDouble(keyboardinput.nextLine());
					Question q4 = new NumQuestion(q4text, q4value, q4tolerance);
					
					System.out.println("Enter the answer of the question: ");
					Answer q4answer = new NumAnswer(keyboardinput.nextLine());
					q4.setRightAnswer(q4answer);
					
					createdExam.addQuestion(q4);
				}
			}
			
			
			else if (userSelection.equalsIgnoreCase("c")) // Remove questions interactively
			{
				
				System.out.println("Enter the number of the question you would like to remove: ");
				System.out.println("(If you would like to remove all questions, enter -1)");
				int questionNumber = Integer.parseInt(keyboardinput.nextLine());
				
				if (questionNumber == -1)
				{
					createdExam.removeQuestion(questionNumber);
				}
				
				else if (questionNumber > 0)
				{
					createdExam.removeQuestion(questionNumber - 1);
				}
			}
			
			
			else if (userSelection.equalsIgnoreCase("d")) // Reorder questions and answers
			{
				createdExam.reorderQuestions();
				createdExam.reorderMCAnswers(-1);
			}
			
			
			else if (userSelection.equalsIgnoreCase("e")) // Print the Exam
			{
				createdExam.print();
			}
			
			
			else if (userSelection.equalsIgnoreCase("f")) // Save the Exam
			{
				try {
					File YourCreatedExamFile = new File("YourCreatedExam.txt");
					YourCreatedExamFile.createNewFile();
					PrintWriter pw = new PrintWriter(YourCreatedExamFile);
					createdExam.save(pw);
					pw.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			else if (userSelection.equalsIgnoreCase("quit"))
			{
				break;
			}
			
			
			System.out.print("\n\n\n");
		}		
	}
	
}