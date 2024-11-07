package enshud.s2.parser;
public class IndexNode extends AstNode {
    private ExpressionNode expressionNode;

    public IndexNode() throws SyntaxException {
        this.expressionNode = null;
    }

    protected void parse(Context context) throws SyntaxException {
        this.expressionNode = new ExpressionNode();
        this.expressionNode.parse(context);
    }

    public ExpressionNode getExpressionNode() {
        return this.expressionNode;
    }
}