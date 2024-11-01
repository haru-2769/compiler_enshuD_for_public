package enshud.s2.parser;

public interface AstNode {
	public abstract void accept(Visitor visitor);
}
