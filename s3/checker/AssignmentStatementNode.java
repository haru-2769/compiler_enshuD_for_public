package enshud.s3.checker;

public class AssignmentStatementNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        LeftHandSideNode leftHandSideNode = new LeftHandSideNode();
        addChild(leftHandSideNode);
        leftHandSideNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SASSIGN")));
        ExpressionNode expressionNode = new ExpressionNode();
        addChild(expressionNode);
        expressionNode.parse(context);
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}