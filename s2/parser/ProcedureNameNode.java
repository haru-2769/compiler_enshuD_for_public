package enshud.s2.parser;

public class ProcedureNameNode extends NonTerminalNode {
	public ProcedureNameNode(Context context) throws SyntaxException {
		parse(context);
	}

	protected void parse(Context context) throws SyntaxException {
		addChild(new TerminalNode(context.checkTerminalSymbol("SIDENTIFIER")));
	}
}