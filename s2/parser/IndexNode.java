package enshud.s2.parser;

public class IndexNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        ExpressionNode expressionNode = new ExpressionNode();
        addChild(expressionNode);
        expressionNode.parse(context);
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}