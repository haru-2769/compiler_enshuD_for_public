package enshud.s2.parser;

public class SyntaxException extends Exception {
	private final String error;
	
	public SyntaxException(Token token) {
		this.error = "Syntax error: line " + token.getLineCount();
	}
	
	@Override
	public String getMessage() {
		return this.error;
	}
}
