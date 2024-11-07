package enshud.s2.parser;

public abstract class AstNode {
	abstract void parse(Context context) throws SyntaxException;
}
