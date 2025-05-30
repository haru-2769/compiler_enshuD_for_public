package enshud.s2.parser;

public class AssignmentStatementNode extends StmtNode {
    private LeftHandSideNode leftHandSideNode;
    private ExpressionNode expressionNode;

    public AssignmentStatementNode() throws SyntaxException {
        this.leftHandSideNode = null;
        this.expressionNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.setLine(context.getLineCount());
        this.leftHandSideNode = new LeftHandSideNode();
        this.leftHandSideNode.parse(context);
        context.checkTerminalSymbol("SASSIGN");
        this.expressionNode = new ExpressionNode();
        this.expressionNode.parse(context);
    }

    public LeftHandSideNode getLeftHandSideNode() {
        return this.leftHandSideNode;
    }

    public ExpressionNode getExpressionNode() {
        return this.expressionNode;
    }

    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}