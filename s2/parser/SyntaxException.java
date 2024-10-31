package enshud.s2.parser;

public class SyntaxException extends Exception {
	private final String error;
	
	public SyntaxException(Token token) {
		super();
		this.error = "Syntax error: line " + token.getLineCount();
	}
	
	public String getError() {
		return this.error;
	}
}
