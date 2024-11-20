build:
	javac -g Servere.java
	javac -g Colorare.java
	javac -g Compresie.java
	javac -g Criptat.java
	javac -g Oferta.java
run-p1:
	java Servere
run-p2:
	java Colorare
run-p3:
	java Compresie
run-p4:
	java Criptat
run-p5:
	java Oferta
clean:
	rm -rf *.class
