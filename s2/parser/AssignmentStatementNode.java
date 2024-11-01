package enshud.s2.parser;

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
}