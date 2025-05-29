package enshud.s2.parser;

public abstract class AstNode {
	Token token;
	
	public AstNode() {
		this.token = null;
	}

	public abstract void parse(Context context) throws SyntaxException;
	public abstract void accept(Visitor visitor) throws SemanticException;

	public Token getToken() {
		return token;
	}
}