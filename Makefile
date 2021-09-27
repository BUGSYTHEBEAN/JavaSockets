JAVAC = javac
JFLAG = -g
.SUFFIXES: .java .class
.java.class:
	$(JAVAC) $(JFLAG) $*.java
CLASSES = \
	Iprefer.java
default: classes
classes: $(CLASSES:.java=.class)
clean: $(RM) *.class

