package enshud.s4.compiler;

public class SyntaxException extends Exception {
	private final String message;
	
	public SyntaxException(Token token) {
		super();
		this.message = "Syntax error: line " + token.getLineCount();
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
