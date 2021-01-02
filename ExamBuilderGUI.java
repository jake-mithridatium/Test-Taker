import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;




public class ExamBuilderGUI {
	public static JFrame frame0;
	public static JPanel pnPanel0;
	public static Exam createdExam;
	public static JTextField textFieldPro;
	
	
	public void initFrame() {
        frame0 = new JFrame("Exam Builder Control Center");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setSize(400, 400);
	}
	
	
	private static class SubmitAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			createdExam = new Exam(textFieldPro.getText());
			frame0 = new JFrame("Exam Builder Control Center");
	        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame0.setSize(400, 400);
		    frame0.getContentPane().add(BorderLayout.CENTER, pnPanel0);
		    frame0.setVisible(true);
		}
	}
	private static ActionListener submit = new SubmitAction();
	
	
	private static class ChooseAction1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    String ans1 = JOptionPane.showInputDialog(frame0, "Please enter the filename of the exam you would like to load: ");
			try {
			    File InputExamFile = new File(ans1);
				Scanner s = new Scanner(InputExamFile);
				createdExam.storeQuestions(s);
				s.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	private static ActionListener choose1 = new ChooseAction1();
	
	
	private static class ChooseAction2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    String questionType = JOptionPane.showInputDialog(frame0, "Enter the type of the question (choose from SA, MCSA, MCMA, Num): ");

			if (questionType.equalsIgnoreCase("sa"))
			{
			    String q1text = JOptionPane.showInputDialog(frame0, "Enter the text of the question: ");
			    String q1valuetemp = JOptionPane.showInputDialog(frame0, "Enter the value of the question: ");
				double q1value = Double.parseDouble(q1valuetemp);
				Question q1 = new SAQuestion(q1text, q1value);
				
			    String q1answertemp = JOptionPane.showInputDialog(frame0, "Enter the answer of the question: ");
				Answer q1answer = new SAAnswer(q1answertemp);
				q1.setRightAnswer(q1answer);
				
				createdExam.addQuestion(q1);
			}
			
			else if (questionType.equalsIgnoreCase("mcsa"))
			{
			    String q2text = JOptionPane.showInputDialog(frame0, "Enter the text of the question: ");
			    String q2valuetemp = JOptionPane.showInputDialog(frame0, "Enter the value of the question: ");
				double q2value = Double.parseDouble(q2valuetemp);
				MCQuestion q2 = new MCSAQuestion(q2text, q2value);
				
			    String q2numtemp = JOptionPane.showInputDialog(frame0, "Enter the number of answer choices: ");
				int numMCAnswers = Integer.parseInt(q2numtemp);
				
				char letter = 'A';
				for (int i = 0; i < numMCAnswers; i++)
				{
				    String q2answertext = JOptionPane.showInputDialog(frame0, "Enter answer choice " + (char)(letter+i) + ": ");
					System.out.print("Enter the value of answer choice " + (char)(letter+i) + ": ");
				    String q2answervaluetemp = JOptionPane.showInputDialog(frame0, "Enter the value of answer choice " + (char)(letter+i) + ": ");
					double q2answervalue = Double.parseDouble(q2answervaluetemp);
					q2.addAnswer(new MCSAAnswer(q2answertext, q2answervalue));
				}
				
				createdExam.addQuestion(q2);
			}
			
			else if (questionType.equalsIgnoreCase("mcma"))
			{
			    String q3text = JOptionPane.showInputDialog(frame0, "Enter the text of the question: ");
			    String q3valuetemp = JOptionPane.showInputDialog(frame0, "Enter the value of the question: ");
				double q3value = Double.parseDouble(q3valuetemp);
			    String q3basetemp = JOptionPane.showInputDialog(frame0, "Enter the base credit of the question: ");
				double q3base = Integer.parseInt(q3basetemp);
				MCQuestion q3 = new MCMAQuestion(q3text, q3value, q3base);
				
			    String q3numtemp = JOptionPane.showInputDialog(frame0, "Enter the number of answer choices: ");
				int numMCAnswers = Integer.parseInt(q3numtemp);
				
				char letter = 'A';
				for (int i = 0; i < numMCAnswers; i++)
				{
				    String q3answertext = JOptionPane.showInputDialog(frame0, "Enter answer choice " + (char)(letter+i) + ": ");
					System.out.print("Enter the value of answer choice " + (char)(letter+i) + ": ");
				    String q3answervaluetemp = JOptionPane.showInputDialog(frame0, "Enter the value of answer choice " + (char)(letter+i) + ": ");
					double q3answervalue = Double.parseDouble(q3answervaluetemp);
					q3.addAnswer(new MCMAAnswer(q3answertext, q3answervalue));
				}
				
				createdExam.addQuestion(q3);
			}
			
			
			else if (questionType.equalsIgnoreCase("num"))
			{
			    String q4text = JOptionPane.showInputDialog(frame0, "Enter the text of the question: ");
			    String q4valuetemp = JOptionPane.showInputDialog(frame0, "Enter the value of the question: ");
				double q4value = Double.parseDouble(q4valuetemp);
			    String q4tolerancetemp = JOptionPane.showInputDialog(frame0, "Enter the tolerance of the question: ");
				double q4tolerance = Double.parseDouble(q4tolerancetemp);
				Question q4 = new NumQuestion(q4text, q4value, q4tolerance);
				
			    String q4answertemp = JOptionPane.showInputDialog(frame0, "Enter the answer of the question: ");
				Answer q4answer = new NumAnswer(q4answertemp);
				q4.setRightAnswer(q4answer);
				
				createdExam.addQuestion(q4);
			}
			
		}
	}
	private static ActionListener choose2 = new ChooseAction2();
	
	
	private static class ChooseAction3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		    String ans3 = JOptionPane.showInputDialog(frame0, "Enter the number of the question you would like to remove: \n" + 
		    		"(If you would like to remove all questions, enter -1)");
			int questionNumber = Integer.parseInt(ans3);
			
			if (questionNumber == -1)
			{
				createdExam.removeQuestion(questionNumber);
			}
			
			else if (questionNumber > 0)
			{
				createdExam.removeQuestion(questionNumber - 1);
			}
		}
	}
	private static ActionListener choose3 = new ChooseAction3();
	
	
	private static class ChooseAction4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			createdExam.reorderQuestions();
			createdExam.reorderMCAnswers(-1);
		    JOptionPane.showMessageDialog(frame0, "Questions and answers successfully reordered!", "Alert", JOptionPane.WARNING_MESSAGE);
		}
	}
	private static ActionListener choose4 = new ChooseAction4();
	
	
	private static class ChooseAction5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JPanel pnPrintExamPanel;
			JLabel lbLabel0;
			JTextArea taArea0;

			pnPrintExamPanel = new JPanel();
			GridBagLayout gbPrintExamPanel = new GridBagLayout();
			GridBagConstraints gbcPrintExamPanel = new GridBagConstraints();
			pnPrintExamPanel.setLayout( gbPrintExamPanel );

			lbLabel0 = new JLabel( "Here is your exam:"  );
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
	        createdExam.print();
		    JOptionPane.showMessageDialog(frame0, pnPrintExamPanel);
		}
	}
	private static ActionListener choose5 = new ChooseAction5();

	
	private static class ChooseAction6 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				File YourCreatedExamFile = new File("YourCreatedExam.txt");
				YourCreatedExamFile.createNewFile();
				PrintWriter pw = new PrintWriter(YourCreatedExamFile);
				createdExam.save(pw);
				pw.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		    JOptionPane.showMessageDialog(frame0, "Exam successfully saved to YourCreatedExam.txt!", "Alert", JOptionPane.WARNING_MESSAGE);
		}
	}
	private static ActionListener choose6 = new ChooseAction6();
	
	
	private static class ChooseAction7 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	private static ActionListener choose7 = new ChooseAction7();

	
	
	
	
	
    public static void main(String[] args) {
        // Creating the Frame
        frame0 = new JFrame("Exam Builder Control Center");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setSize(400, 400);
        
	    	JLabel lbLabel0;
		JButton btBut0;
	    	GridBagLayout gbPanel0;
	    	GridBagConstraints gbcPanel0;
	
	    	pnPanel0 = new JPanel();
	    gbPanel0 = new GridBagLayout();
	    	gbcPanel0 = new GridBagConstraints();
	    	pnPanel0.setLayout( gbPanel0 );
	
	    	lbLabel0 = new JLabel( "Please enter a filename for your exam: "  );
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
	
	    	textFieldPro = new JTextField( );
	    	gbcPanel0.gridx = 8;
	    	gbcPanel0.gridy = 11;
	    	gbcPanel0.gridwidth = 2;
	    	gbcPanel0.gridheight = 1;
	    	gbcPanel0.fill = GridBagConstraints.BOTH;
	    	gbcPanel0.weightx = 1;
	    	gbcPanel0.weighty = 0;
	    	gbcPanel0.anchor = GridBagConstraints.NORTH;
	    	gbPanel0.setConstraints(textFieldPro, gbcPanel0 );
	    	pnPanel0.add(textFieldPro);
	    	
	    	btBut0 = new JButton( "Submit"  );
		gbcPanel0.gridx = 8;
		gbcPanel0.gridy = 12;
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

		JButton option1button;
		JButton option2button;
		JButton option3button;
		JButton option4button;
		JButton option5button;
		JButton option6button;
		JButton option7button;
		JLabel lbLabel2;

		pnPanel0 = new JPanel();
		gbPanel0 = new GridBagLayout();
		gbcPanel0 = new GridBagConstraints();
		pnPanel0.setLayout( gbPanel0 );

		option1button = new JButton( "Load questions from a saved exam file"  );
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 6;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints(option1button, gbcPanel0 );
		pnPanel0.add(option1button);

		option2button = new JButton( "Add questions interactively"  );
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 7;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( option2button, gbcPanel0 );
		pnPanel0.add( option2button );

		option3button = new JButton( "Remove questions interactively"  );
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 8;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( option3button, gbcPanel0 );
		pnPanel0.add( option3button );

		option4button = new JButton( "Reorder questions & answers"  );
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 9;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( option4button, gbcPanel0 );
		pnPanel0.add( option4button );

		option5button = new JButton( "Print the exam"  );
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 10;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( option5button, gbcPanel0 );
		pnPanel0.add( option5button );

		option6button = new JButton( "Save the exam"  );
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 11;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( option6button, gbcPanel0 );
		pnPanel0.add( option6button );
		
		option7button = new JButton( "Quit"  );
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 12;
		gbcPanel0.gridwidth = 1;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.BOTH;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 0;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( option7button, gbcPanel0 );
		pnPanel0.add( option7button );

		lbLabel2 = new JLabel( "What action would you like to do?"  );
		gbcPanel0.gridx = 2;
		gbcPanel0.gridy = 5;
		gbcPanel0.gridwidth = 6;
		gbcPanel0.gridheight = 1;
		gbcPanel0.fill = GridBagConstraints.VERTICAL;
		gbcPanel0.weightx = 1;
		gbcPanel0.weighty = 1;
		gbcPanel0.anchor = GridBagConstraints.NORTH;
		gbPanel0.setConstraints( lbLabel2, gbcPanel0 );
		pnPanel0.add( lbLabel2 );
		
		btBut0.addActionListener(submit);
		option1button.addActionListener(choose1);
		option2button.addActionListener(choose2);
		option3button.addActionListener(choose3);
		option4button.addActionListener(choose4);
		option5button.addActionListener(choose5);
		option6button.addActionListener(choose6);
		option7button.addActionListener(choose7);

    }
}