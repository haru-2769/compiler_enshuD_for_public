package enshud.s2.parser;

public class AssignmentStatementNode extends NonTerminalNode {
    public AssignmentStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new LeftHandSideNode(context));
        context.checkTerminalSymbol("SASSIGN");
        addChild(new ExpressionNode(context));
    }
}