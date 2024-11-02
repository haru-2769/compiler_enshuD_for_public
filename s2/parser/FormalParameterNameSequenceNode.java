package enshud.s2.parser;

public class FormalParameterNameSequenceNode extends NonTerminalNode{
	public FormalParameterNameSequenceNode(Context context) throws SyntaxException {
		parse(context);
	}
	
	protected void parse(Context context) throws SyntaxException {
		addChild(new FormalParameterNameNode(context));
		while (context.equalsAny(0, "SCOMMA")) {
			context.checkTerminalSymbol("SCOMMA");
			addChild(new FormalParameterNameNode(context));
		}
	}
}