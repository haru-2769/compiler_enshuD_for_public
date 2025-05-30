package enshud.s2.parser;

import java.util.ArrayList;
import java.util.List;


public class IfNode extends StmtNode {
	private ExpressionNode expressionNode;
	private List<CompoundStatementNode> compoundStatementNodes;

	public IfNode() {
		this.expressionNode = null;
		this.compoundStatementNodes = new ArrayList<>();
	}

	@Override
	public void parse(Context context) throws SyntaxException {
		this.setLine(context.getLineCount());
		context.checkTerminalSymbol("SIF");
        this.expressionNode = new ExpressionNode();
        this.expressionNode.parse(context);
        context.checkTerminalSymbol("STHEN");
        CompoundStatementNode compoundStatementNode = new CompoundStatementNode();
    	compoundStatementNode.parse(context);
		this.compoundStatementNodes.add(compoundStatementNode);
		if (context.equalsAny(0, "SELSE")) {
			context.checkTerminalSymbol("SELSE");
			compoundStatementNode = new CompoundStatementNode();
			compoundStatementNode.parse(context);
			this.compoundStatementNodes.add(compoundStatementNode);
		}
	}

	public ExpressionNode getExpressionNode() {
		return this.expressionNode;
	}

	public List<CompoundStatementNode> getCompoundStatementNodes() {
		return this.compoundStatementNodes;
	}

	@Override
	public void accept(Visitor visitor) throws SemanticException {
		visitor.visit(this);
	}
}
