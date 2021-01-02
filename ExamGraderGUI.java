import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;




public class ExamGraderGUI {
    public static JFrame frame0;
    public static JPanel pnPanel0;
    public static Exam takenExam;
    public static JTextField textFieldPro1;
    public static JTextField textFieldPro2;

    
    
    public void initFrame() {
        frame0 = new JFrame("Exam Grader Control Center");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setSize(400, 400);
    }
    

    private static class SubmitAction implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {
                String getText = "";
                File afile = new File("");
        
                getText = textFieldPro1.getText();
                afile = new File(getText);
                Scanner scan = new Scanner(afile);
                Exam e = new Exam(scan);
                scan.close();
                
                File sfile = new File("");
                
                String text = textFieldPro2.getText();
                String test;
                String test1;
                Scanner confirm1 = new Scanner(afile);
                
                test = confirm1.nextLine();
                confirm1.close();
        
        
                sfile = new File(text);
                Scanner confirm2 = new Scanner(sfile);
                confirm2.nextLine();
                test1 = confirm2.nextLine();
                confirm2.close();
                
                Scanner ascan = new Scanner(sfile);
                e.restoreStudentAnswers(ascan);
                e.reportQuestionValues(); //Report the score for the exam
                
                ascan.close();
                
                //Stores everything into CSV files
                PrintWriter csvwriter = null;
                try {
                    csvwriter = new PrintWriter(new File("cs342.csv"));
                }
                catch (FileNotFoundException not) {
                    not.printStackTrace();
                }
                e.CSVgenerator(csvwriter);
                csvwriter.close();
                
                JOptionPane.showMessageDialog(frame0, "Grading Complete!", "Alert", JOptionPane.WARNING_MESSAGE);
                
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    private static ActionListener submit = new SubmitAction();
    
    
    
    
    
    
    
    public static void main(String[] args) {
        // Creating the Frame
        frame0 = new JFrame("Exam Grader Control Center");
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
    
            lbLabel0 = new JLabel( "Enter the name of the Exam file"  );
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
            
            lbLabel1 = new JLabel( "Enter the name of the student answer file"  );
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