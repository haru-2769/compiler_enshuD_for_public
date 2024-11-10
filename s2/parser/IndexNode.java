package enshud.s2.parser;
public class IndexNode extends AstNode {
    private ExpressionNode expressionNode;

    public IndexNode() {
        this.expressionNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.expressionNode = new ExpressionNode();
        this.expressionNode.parse(context);
    }

    public ExpressionNode getExpressionNode() {
        return this.expressionNode;
    }
}