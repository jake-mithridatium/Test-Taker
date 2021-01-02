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



public class ExamTaker {
	
	public static void main(String[] args) {
		System.out.println("GROUP 48");
		System.out.println("Mohammad Abuosbie (mabuos2)");
		System.out.println("Jacob McKibben (jmckib2)");
		System.out.println("Jacob Zaworski (jzawor2)");
		
		
		System.out.print("\n\n\n\n");
		
		try {
			Scanner keyboardinput = ScannerFactory.getKeyboardScanner(); // reads from System.in
			Exam takenExam = new Exam("Taken Exam");
			takenExam.getInfoFromStudent();
			String tempSN = takenExam.studentName;
			String tempFN = takenExam.fileName;
	
			
			File InputExamFile = new File(tempFN);
			Scanner s = new Scanner(InputExamFile);
			takenExam = new Exam(s);
			takenExam.studentName = tempSN;
			takenExam.fileName = tempFN;
			s.close();
			
			
			ArrayList<Integer> skipped = takenExam.getAnswerFromStudent(keyboardinput);
			

			for (int j = 0; j < skipped.size(); j++)
			{
				takenExam.getAnswerFromStudent(skipped.get(j));
			}

	
			System.out.print("\n\n\n");
			
			File StudentAnswersFile = new File("StudentAnswers.txt");
			StudentAnswersFile.createNewFile();
			PrintWriter pw = new PrintWriter(StudentAnswersFile);
			takenExam.saveStudentAnswers(pw);
			pw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}