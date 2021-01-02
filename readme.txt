/*
 * GROUP 48
 * Mohammad Abuosbie (mabuos2)
 * Jacob McKibben (jmckib2)
 * Jacob Zaworski (jzawor2)
 * 
 * Term Project (Part 5)
 */




CS 342 Project (Part 5) ReadMe




*********************************************

RESPONSIBILITIES


ExamBuilderGUI
- completed by Mohammad Abuosbie (mabuos2)


ExamTakerGUI
- completed by Jacob McKibben (jmckib2) & Jacob Zaworski (jzawor2)


ExamGraderGUI
- completed by Jacob McKibben (jmckib2) & Jacob Zaworski (jzawor2)



*********************************************

HOW TO RUN

In order to run the code, simply type "make" in the correct directory. That will create several .class files.

ExamBuilder (mabuos2)
- In order to run ExamBuilder, type "java ExamBuilderGUI"
- Results will be output to "YourCreatedExam.txt"
- Optional Enhancement: Read in multiple Exams, and merge the questions into one Exam.
- Optional Enhancement: Read in file(s), but interactively decide which question(s) to add to the exam.

ExamTaker
- In order to run ExamTaker, type "java ExamTakerGUI"
- Results will be output to "StudentAnswers.txt"
- To skip a question, simply type "skip"

ExamGrader
- In order to run ExamGrader, type "java ExamGraderGUI"
- Results will be printed to System.out
- Results will also be printed to cs342.csv
- Be sure that the Exam file and the Answers file are matched with each other



*********************************************

NOTE
- Added a CustomOutputStream class to redirect some of the OutputStream to a GUI TextBox
