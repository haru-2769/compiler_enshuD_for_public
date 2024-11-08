package enshud.s3.checker;

public class AssignmentStatementNode extends AstNode {
    private LeftHandSideNode leftHandSideNode;
    private ExpressionNode expressionNode;

    public AssignmentStatementNode() throws SyntaxException {
        this.leftHandSideNode = null;
        this.expressionNode = null;
    }

    public void parse(Context context) throws SyntaxException {
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

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}