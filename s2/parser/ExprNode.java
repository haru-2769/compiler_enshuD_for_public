package enshud.s2.parser;

public abstract class ExprNode extends AstNode{
	private String line;
	private TypeEnum type;

	public abstract void parse(Context context) throws SyntaxException;
	public abstract void accept(Visitor visitor) throws SemanticException;

	public void setLine(String line) {
		this.line = line;
	}

	public String getLine() {
		return this.line;
	}

	public void setType(TypeEnum type) {
		this.type = type;
	}

	public TypeEnum getType() {
		return this.type;
	}
}
