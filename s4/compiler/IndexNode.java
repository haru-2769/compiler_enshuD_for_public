package enshud.s4.compiler;

public class IndexNode extends AstNode {
    private ExpressionNode expressionNode;

    public IndexNode() throws SyntaxException {
        this.expressionNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.expressionNode = new ExpressionNode();
        this.expressionNode.parse(context);
    }

    public ExpressionNode getExpressionNode() {
        return this.expressionNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}