package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.List;


public class IfNode extends StmtNode {
	ExpressionNode expressionNode;
	List<CompoundStatementNode> compoundStatementNodes;

	public IfNode() {
		this.expressionNode = null;
		this.compoundStatementNodes = new ArrayList<>();
	}

	@Override
	public void parse(Context context) throws SyntaxException {
		this.setLine(context.checkTerminalSymbol("SIF").getLineCount());
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

	@Override
	public void accept(Visitor visitor) throws SemanticException {
		visitor.visit(this);
	}

	public ExpressionNode getExpressionNode() {
		return this.expressionNode;
	}

	public List<CompoundStatementNode> getCompoundStatementNodes() {
		return this.compoundStatementNodes;
	}
}
