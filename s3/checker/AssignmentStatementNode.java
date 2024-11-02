package enshud.s3.checker;

public class AssignmentStatementNode extends AstNode {
    private LeftHandSideNode leftHandSideNode;
    private ExpressionNode expressionNode;

    public AssignmentStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        leftHandSideNode = new LeftHandSideNode(context);
        context.checkTerminalSymbol("SASSIGN");
        expressionNode = new ExpressionNode(context);
    }

    public LeftHandSideNode getLeftHandSideNode() {
        return leftHandSideNode;
    }

    public ExpressionNode getExpressionNode() {
        return expressionNode;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}