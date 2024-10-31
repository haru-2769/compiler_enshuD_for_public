package enshud.s3.checker;

public class SyntaxException extends Exception {
	private final String error;
	
	public SyntaxException(Token token) {
		this.error = "Syntax error: line " + token.getLineCount();
	}
	
	public String getError() {
		return this.error;
	}
}
