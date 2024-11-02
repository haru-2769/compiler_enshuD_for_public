package enshud.s3.checker;

public class ProcedureNameNode extends NonTerminalNode {
	public ProcedureNameNode(Context context) throws SyntaxException {
		parse(context);
	}

	protected void parse(Context context) throws SyntaxException {
		addChild(new TerminalNode(context.checkTerminalSymbol("SIDENTIFIER")));
	}

	public void accept(Visitor visitor) throws SemanticException {
		visitor.visit(this);
	}
}