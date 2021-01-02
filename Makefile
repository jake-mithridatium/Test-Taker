JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Answer.java \
	MCAnswer.java \
	MCSAAnswer.java \
	MCMAAnswer.java \
	SAAnswer.java \
	NumAnswer.java \
	Question.java \
	MCQuestion.java \
	MCSAQuestion.java \
	MCMAQuestion.java \
	SAQuestion.java \
	NumQuestion.java \
	Exam.java \
	ScannerFactory.java \
	CustomOutputStream.java \
	ExamBuilder.java \
	ExamTaker.java \
	ExamGrader.java \
	ExamBuilderGUI.java \
	ExamTakerGUI.java \
	ExamGraderGUI.java 

MAIN = \ ExamBuilderGUI \ ExamTakerGUI \ ExamGraderGUI

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class