/*
 * GROUP 48
 * Mohammad Abuosbie (mabuos2)
 * Jacob McKibben (jmckib2)
 * Jacob Zaworski (jzawor2)
 * 
 * Term Project (Part 4)
 */




import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExamGrader {

	public static void main(String[] args) {
		System.out.println("GROUP 48");
		System.out.println("Mohammad Abuosbie (mabuos2)");
		System.out.println("Jacob McKibben (jmckib2)");
		System.out.println("Jacob Zaworski (jzawor2)");

		System.out.print("\n\n\n\n");
		
		try {
	        String getText = "";
	        File afile = new File("");
	
	        while(getText.equals(""))
	        {
	            System.out.println("Enter the name of the Exam file");
	            getText = ScannerFactory.getKeyboardScanner().nextLine();
	            if (getText.equals(""))
	                System.out.println("Unable to find file.");
	            else{
	                afile = new File(getText);
	                break;
	            }
	        }
	
	        Scanner scan = new Scanner(afile);
	        Exam e = new Exam(scan);
	
	
	        scan.close();
	        System.out.println("Exam gotten succesfully.");
	        String text = "";
	
	        File sfile = new File("");
	        System.out.println("Enter the name of the student answer file");
	        text = ScannerFactory.getKeyboardScanner().nextLine();
	        String test;
	        String test1;
	       Scanner confirm1 = new Scanner(afile);
	
	        test = confirm1.nextLine();
	        confirm1.close();
	
	
	        System.out.println(text);
	        sfile = new File(text);
	        Scanner confirm2 = new Scanner(sfile);
	        confirm2.nextLine();
	        test1 = confirm2.nextLine();
	
	        confirm2.close();
	        
	        //Used for match debugging
	        /*System.out.println(test + " 33333 " + test1);
	        if(test.equals(test1)){
	            System.out.println("CONFIRMED MATCHING EXAM AND ANSWERS");
	
	        }else {
	            System.out.println("NOT MATCHING EXAM AND ANSWERS EXITING..");
	            System.exit(0);
	        }*/
	
	        Scanner ascan = new Scanner(sfile);
	        e.restoreStudentAnswers(ascan);
	        e.reportQuestionValues(); //Report the score for the exam
	        
	        ascan.close();
	        
	        //Stores everything into CSV files
	        System.out.println("Storing the CSV file cs342.csv");
	        PrintWriter csvwriter = null;
	        try {
	            csvwriter = new PrintWriter(new File("cs342.csv"));
	        }
	        catch (FileNotFoundException not) {
	            not.printStackTrace();
	        }
	        e.CSVgenerator(csvwriter);
	        csvwriter.close();
	}
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	}
}
