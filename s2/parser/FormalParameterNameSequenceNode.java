package enshud.s2.parser;

import java.util.List;
import java.util.ArrayList;

public class FormalParameterNameSequenceNode extends AstNode{
	private FormalParameterNameNode formalParameterNameNode;
	private List<FormalParameterNameNode> formalParameterNameNodes;

	public FormalParameterNameSequenceNode() throws SyntaxException {
		this.formalParameterNameNodes = new ArrayList<>();
	}
	
	protected void parse(Context context) throws SyntaxException {
		this.formalParameterNameNode = new FormalParameterNameNode();
		this.formalParameterNameNodes.add(this.formalParameterNameNode);
		this.formalParameterNameNode.parse(context);
		while (context.equalsAny(0, "SCOMMA")) {
			context.checkTerminalSymbol("SCOMMA");
			this.formalParameterNameNode = new FormalParameterNameNode();
			this.formalParameterNameNodes.add(this.formalParameterNameNode);
			this.formalParameterNameNode.parse(context);
		}
	}

	public List<FormalParameterNameNode> getFormalParameterNameNodes() {
		return this.formalParameterNameNodes;
	}
}