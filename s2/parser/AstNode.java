package enshud.s2.parser;

public interface AstNode {
	void accept(Visitor visitor);
}
