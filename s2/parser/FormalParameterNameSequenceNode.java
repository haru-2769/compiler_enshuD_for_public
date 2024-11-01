package enshud.s2.parser;

public class FormalParameterNameSequenceNode extends NonTerminalNode{
	public void parse(Context context) throws SyntaxException {
		FormalParameterNameNode formalParameterNameNode = new FormalParameterNameNode();
		formalParameterNameNode.parse(context);
		addChild(formalParameterNameNode);
		while (context.equalsAny(0, "SCOMMA")) {
			addChild(new TerminalNode(context.checkTerminalSymbol("SCOMMA")));
			FormalParameterNameNode formalParameterNameNode1 = new FormalParameterNameNode();
			formalParameterNameNode1.parse(context);
			addChild(formalParameterNameNode1);
		}
	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}