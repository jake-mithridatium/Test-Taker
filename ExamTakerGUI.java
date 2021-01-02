import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;




public class ExamTakerGUI {
	public static JFrame frame0;
	public static JPanel pnPanel0;
	public static Exam takenExam;
	public static JTextField textFieldPro1;
	public static JTextField textFieldPro2;

	
	
	public void initFrame() {
        frame0 = new JFrame("Exam Taker Control Center");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setSize(400, 400);
	}
	
	
	private static class SubmitAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Exam takenExam = new Exam("Taken Exam");
				takenExam.studentName =  textFieldPro1.getText();
				takenExam.fileName =  textFieldPro2.getText();
				String tempSN = takenExam.studentName;
				String tempFN = takenExam.fileName;
				
				File InputExamFile = new File(tempFN);
				Scanner s = new Scanner(InputExamFile);
				takenExam = new Exam(s);
				takenExam.studentName = tempSN;
				takenExam.fileName = tempFN;
				s.close();
				
				for (int i = 0; i < takenExam.questions.size(); i++)
				{
					String qans;
				    
				    if (takenExam.questions.get(i) instanceof SAQuestion) {
						qans = JOptionPane.showInputDialog(frame0, takenExam.questions.get(i).text);
				    		takenExam.questions.get(i).studentAnswer = new SAAnswer(qans);
				    }
				    
				    else if (takenExam.questions.get(i) instanceof MCSAQuestion) {
						JPanel pnPrintExamPanel;
						JLabel lbLabel0;
						JTextArea taArea0;

						pnPrintExamPanel = new JPanel();
						GridBagLayout gbPrintExamPanel = new GridBagLayout();
						GridBagConstraints gbcPrintExamPanel = new GridBagConstraints();
						pnPrintExamPanel.setLayout( gbPrintExamPanel );

						lbLabel0 = new JLabel( "Input a letter choice:" );
						gbcPrintExamPanel.gridx = 8;
						gbcPrintExamPanel.gridy = 9;
						gbcPrintExamPanel.gridwidth = 1;
						gbcPrintExamPanel.gridheight = 1;
						gbcPrintExamPanel.fill = GridBagConstraints.BOTH;
						gbcPrintExamPanel.weightx = 1;
						gbcPrintExamPanel.weighty = 1;
						gbcPrintExamPanel.anchor = GridBagConstraints.NORTH;
						gbPrintExamPanel.setConstraints( lbLabel0, gbcPrintExamPanel );
						pnPrintExamPanel.add( lbLabel0 );

						taArea0 = new JTextArea(2,10);
						gbcPrintExamPanel.gridx = 8;
						gbcPrintExamPanel.gridy = 10;
						gbcPrintExamPanel.gridwidth = 1;
						gbcPrintExamPanel.gridheight = 1;
						gbcPrintExamPanel.fill = GridBagConstraints.BOTH;
						gbcPrintExamPanel.weightx = 1;
						gbcPrintExamPanel.weighty = 1;
						gbcPrintExamPanel.anchor = GridBagConstraints.NORTH;
						gbPrintExamPanel.setConstraints( taArea0, gbcPrintExamPanel );
						pnPrintExamPanel.add( taArea0 );
						
				        PrintStream printStream = new PrintStream(new CustomOutputStream(taArea0));
				         
				        // re-assigns standard output stream and error output stream
				        System.setOut(printStream);
				        System.setErr(printStream);
				        takenExam.questions.get(i).print();
				        
						qans = JOptionPane.showInputDialog(frame0, pnPrintExamPanel);
						String t = qans;

						char letter = Character.toUpperCase(t.charAt(0));
						int choice = letter - 'A';
						
						if (choice < 0 || choice >= ((MCQuestion) takenExam.questions.get(i)).answers.size())
						{
							return;
						}
						
						takenExam.questions.get(i).studentAnswer = ((MCQuestion) takenExam.questions.get(i)).answers.get(choice);
				    }
				    
				    else if (takenExam.questions.get(i) instanceof MCMAQuestion) {
						JPanel pnPrintExamPanel;
						JLabel lbLabel0;
						JTextArea taArea0;

						pnPrintExamPanel = new JPanel();
						GridBagLayout gbPrintExamPanel = new GridBagLayout();
						GridBagConstraints gbcPrintExamPanel = new GridBagConstraints();
						pnPrintExamPanel.setLayout( gbPrintExamPanel );

						lbLabel0 = new JLabel( "Input a letter choice separated by spaces:"  );
						gbcPrintExamPanel.gridx = 8;
						gbcPrintExamPanel.gridy = 9;
						gbcPrintExamPanel.gridwidth = 1;
						gbcPrintExamPanel.gridheight = 1;
						gbcPrintExamPanel.fill = GridBagConstraints.BOTH;
						gbcPrintExamPanel.weightx = 1;
						gbcPrintExamPanel.weighty = 1;
						gbcPrintExamPanel.anchor = GridBagConstraints.NORTH;
						gbPrintExamPanel.setConstraints( lbLabel0, gbcPrintExamPanel );
						pnPrintExamPanel.add( lbLabel0 );

						taArea0 = new JTextArea(2,10);
						gbcPrintExamPanel.gridx = 8;
						gbcPrintExamPanel.gridy = 10;
						gbcPrintExamPanel.gridwidth = 1;
						gbcPrintExamPanel.gridheight = 1;
						gbcPrintExamPanel.fill = GridBagConstraints.BOTH;
						gbcPrintExamPanel.weightx = 1;
						gbcPrintExamPanel.weighty = 1;
						gbcPrintExamPanel.anchor = GridBagConstraints.NORTH;
						gbPrintExamPanel.setConstraints( taArea0, gbcPrintExamPanel );
						pnPrintExamPanel.add( taArea0 );
						
				        PrintStream printStream = new PrintStream(new CustomOutputStream(taArea0));
				         
				        // re-assigns standard output stream and error output stream
				        System.setOut(printStream);
				        System.setErr(printStream);
				        
				        takenExam.questions.get(i).print();

				        
						qans = JOptionPane.showInputDialog(frame0, pnPrintExamPanel);
						String t = qans;
						String[] t2 = t.split(" ");
						
						for (int k = 0; k < t2.length; k++)
						{
							char letter = Character.toUpperCase(t2[k].charAt(0));
							int choice = letter - 'A';
							
							if (choice < 0 || choice >= ((MCQuestion) takenExam.questions.get(i)).answers.size())
							{
								return;
							}
							
							((MCMAQuestion)takenExam.questions.get(i)).studentAnswers.add(((MCQuestion) takenExam.questions.get(i)).answers.get(choice));
						}
				    }
				    
				    
				    else if (takenExam.questions.get(i) instanceof NumQuestion) {
						qans = JOptionPane.showInputDialog(frame0, takenExam.questions.get(i).text);
			    			takenExam.questions.get(i).studentAnswer = new NumAnswer(qans);
				    }
				    
				}
				
				File StudentAnswersFile = new File("StudentAnswers.txt");
				StudentAnswersFile.createNewFile();
				PrintWriter pw = new PrintWriter(StudentAnswersFile);
				takenExam.saveStudentAnswers(pw);
				pw.close();
				
			    JOptionPane.showMessageDialog(frame0, "Answers successfully saved to StudentAnswers.txt!", "Alert", JOptionPane.WARNING_MESSAGE);
				
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
			
			frame0 = new JFrame("Exam Taker Control Center");
	        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame0.setSize(400, 400);
		    frame0.getContentPane().add(BorderLayout.CENTER, pnPanel0);
		    frame0.setVisible(true);
		}
	}
	private static ActionListener submit = new SubmitAction();
	
	
	
	
	
	
	
    public static void main(String[] args) {
        // Creating the Frame
        frame0 = new JFrame("Exam Taker Control Center");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setSize(400, 400);
        
	    	JLabel lbLabel0;
	    	JLabel lbLabel1;
		JButton btBut0;
	    	GridBagLayout gbPanel0;
	    	GridBagConstraints gbcPanel0;
	
	    	pnPanel0 = new JPanel();
	    gbPanel0 = new GridBagLayout();
	    	gbcPanel0 = new GridBagConstraints();
	    	pnPanel0.setLayout( gbPanel0 );
	
	    	lbLabel0 = new JLabel( "What is your name?: "  );
	    	gbcPanel0.gridx = 8;
	    	gbcPanel0.gridy = 10;
	    	gbcPanel0.gridwidth = 1;
	    	gbcPanel0.gridheight = 1;
	    	gbcPanel0.fill = GridBagConstraints.BOTH;
	    	gbcPanel0.weightx = 1;
	    	gbcPanel0.weighty = 1;
	    	gbcPanel0.anchor = GridBagConstraints.NORTH;
	    	gbPanel0.setConstraints( lbLabel0, gbcPanel0 );
	    	pnPanel0.add( lbLabel0 );
	
	    	textFieldPro1 = new JTextField( );
	    	gbcPanel0.gridx = 8;
	    	gbcPanel0.gridy = 11;
	    	gbcPanel0.gridwidth = 2;
	    	gbcPanel0.gridheight = 1;
	    	gbcPanel0.fill = GridBagConstraints.BOTH;
	    	gbcPanel0.weightx = 1;
	    	gbcPanel0.weighty = 0;
	    	gbcPanel0.anchor = GridBagConstraints.NORTH;
	    	gbPanel0.setConstraints(textFieldPro1, gbcPanel0 );
	    	pnPanel0.add(textFieldPro1);
	    	
	    	lbLabel1 = new JLabel( "What is the filename of the exam you are answering? "  );
	    	gbcPanel0.gridx = 8;
	    	gbcPanel0.gridy = 12;
	    	gbcPanel0.gridwidth = 1;
	    	gbcPanel0.gridheight = 1;
	    	gbcPanel0.fill = GridBagConstraints.BOTH;
	    	gbcPanel0.weightx = 1;
	    	gbcPanel0.weighty = 1;
	    	gbcPanel0.anchor = GridBagConstraints.NORTH;
	    	gbPanel0.setConstraints( lbLabel1, gbcPanel0 );
	    	pnPanel0.add( lbLabel1 );
	
	    	textFieldPro2 = new JTextField( );
	    	gbcPanel0.gridx = 8;
	    	gbcPanel0.gridy = 13;
	    	gbcPanel0.gridwidth = 2;
	    	gbcPanel0.gridheight = 1;
	    	gbcPanel0.fill = GridBagConstraints.BOTH;
	    	gbcPanel0.weightx = 1;
	    	gbcPanel0.weighty = 0;
	    	gbcPanel0.anchor = GridBagConstraints.NORTH;
	    	gbPanel0.setConstraints(textFieldPro2, gbcPanel0 );
	    	pnPanel0.add(textFieldPro2);
	    	
	    	btBut0 = new JButton( "Submit"  );
		gbcPanel0.gridx = 8;
		gbcPanel0.gridy = 14;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( btBut0, gbcPanel0 );
		pnPanel0.add( btBut0 );
	    	
	    // Adding Components to the frame
	    frame0.getContentPane().add(BorderLayout.CENTER, pnPanel0);
	    frame0.setVisible(true);
	    
		btBut0.addActionListener(submit);
	    
	    
	    
	    
	    
    }
}