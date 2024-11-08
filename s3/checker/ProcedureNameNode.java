package enshud.s3.checker;

public class ProcedureNameNode extends AstNode {
	private Token token;

	public ProcedureNameNode() throws SyntaxException {
		this.token = null;
	}

	public void parse(Context context) throws SyntaxException {
		this.token = context.checkTerminalSymbol("SIDENTIFIER");
	}

	public Token getToken() {
		return this.token;
	}
	
	public void accept(Visitor visitor) throws SemanticException {
		visitor.visit(this);
	}
}