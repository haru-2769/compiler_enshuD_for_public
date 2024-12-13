package enshud.s4.compiler;

public abstract class AstNode {
	public abstract void parse(Context context) throws SyntaxException;
	public abstract void accept(Visitor visitor) throws SemanticException;
}