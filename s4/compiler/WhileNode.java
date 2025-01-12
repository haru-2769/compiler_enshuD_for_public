package enshud.s4.compiler;

public class WhileNode extends StmtNode {
	private int labelCount;
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

	@Override
	public void accept(Visitor visitor) throws SemanticException {
		visitor.visit(this);
	}

	public void setLabelCount(int labelCount) {
		this.labelCount = labelCount;
	}

	public int getLabelCount() {
		return this.labelCount;
	}

	public ExpressionNode getExpressionNode() {
		return this.expressionNode;
	}

	public CompoundStatementNode getCompoundStatementNode() {
		return this.compoundStatementNode;
	}
}
