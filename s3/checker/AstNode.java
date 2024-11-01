package enshud.s3.checker;

public interface AstNode {
	void accept(Visitor visitor) throws SemanticException;
}
