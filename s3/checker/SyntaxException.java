package enshud.s3.checker;

public class SyntaxException extends Exception {
	private final String error;
	
	public SyntaxException(Token token) {
		super();
		this.error = "Syntax error: line " + token.getLineCount();
	}
	
	@Override
	public String getMessage() {
		return this.error;
	}
}
