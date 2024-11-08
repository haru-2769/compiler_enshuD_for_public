package enshud.s2.parser;

public abstract class AstNode {
	public abstract void parse(Context context) throws SyntaxException;
}
