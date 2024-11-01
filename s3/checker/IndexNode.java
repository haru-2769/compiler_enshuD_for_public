package enshud.s3.checker;
public class IndexNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        ExpressionNode expressionNode = new ExpressionNode();
        addChild(expressionNode);
        expressionNode.parse(context);
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}