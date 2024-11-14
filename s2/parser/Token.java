package enshud.s2.parser;

public class Token {
	private String[] elements;
	
	public Token(String line) {
		this.elements = line.split("\t");
	}
	
	public String getLexical() {
		return this.elements[0];
	}
	
	public String getTokenName() {
		return this.elements[1];
	}
	
	public String getID() {
		return this.elements[2];
	}
	
	public String getLineCount() {
		return this.elements[3];
	}
}
