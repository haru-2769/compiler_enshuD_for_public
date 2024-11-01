package enshud.s2.parser;

public class ProcedureNameNode extends NonTerminalNode {
	public void parse(Context context) throws SyntaxException {
		addChild(new TerminalNode(context.checkTerminalSymbol("SIDENTIFIER")));
	}
}