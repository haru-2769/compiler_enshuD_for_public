package enshud.s2.parser;

public class WhileNode extends StmtNode {
	private ExpressionNode expressionNode;
	private CompoundStatementNode compoundStatementNode;

	@Override
	public void parse(Context context) throws SyntaxException {
		this.setLine(context.getLineCount());
		context.checkTerminalSymbol("SWHILE");
        this.expressionNode = new ExpressionNode();
        this.expressionNode.parse(context);
        context.checkTerminalSymbol("SDO");
        this.compoundStatementNode = new CompoundStatementNode();
        this.compoundStatementNode.parse(context);
	}

	public ExpressionNode getExpressionNode() {
		return this.expressionNode;
	}

	public CompoundStatementNode getCompoundStatementNode() {
		return this.compoundStatementNode;
	}
	
	@Override
	public void accept(Visitor visitor) throws SemanticException {
		visitor.visit(this);
	}
}
