package enshud.s3.checker;

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

	public void accept(Visitor visitor) throws SemanticException {
		visitor.visit(this);
	}
}