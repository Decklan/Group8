
ChocAn: ChocAn.java
	javac *.java

clean:
	-rm *.class ${GENERATED}

run:
	java -classpath postgresql-9.4-1204.jdbc4.jar:. ChocAn	
