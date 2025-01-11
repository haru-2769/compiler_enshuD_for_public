package enshud.s4.compiler;

public class WhileNode extends StmtNode {
	ExpressionNode expressionNode;
	CompoundStatementNode compoundStatementNode;

	@Override
	public void parse(Context context) throws SyntaxException {
		setLine(context.checkTerminalSymbol("SWHILE").getLineCount());
        this.expressionNode = new ExpressionNode();
        this.expressionNode.parse(context);
        context.checkTerminalSymbol("SDO");
        this.compoundStatementNode = new CompoundStatementNode();
        this.compoundStatementNode.parse(context);
	}

	@Override
	public void accept(Visitor visitor) throws SemanticException {
		visitor.visit(this);
	}

	public ExpressionNode getExpressionNode() {
		return this.expressionNode;
	}

	public CompoundStatementNode getCompoundStatementNode() {
		return this.compoundStatementNode;
	}

}
