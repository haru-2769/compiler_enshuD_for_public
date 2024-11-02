package enshud.s3.checker;

public abstract class AstNode {
	abstract void parse(Context context) throws SyntaxException;
	abstract void accept(Visitor visitor) throws SemanticException;
}
