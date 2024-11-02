package enshud.s3.checker;

public class AssignmentStatementNode extends NonTerminalNode {
    public AssignmentStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new LeftHandSideNode(context));
        addChild(new TerminalNode(context.checkTerminalSymbol("SASSIGN")));
        addChild(new ExpressionNode(context));
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}