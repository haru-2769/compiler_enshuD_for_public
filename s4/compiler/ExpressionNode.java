package enshud.s4.compiler;

public class ExpressionNode extends ExprNode {
    private SimpleExpressionNode leftSimpleExpressionNode;
    private RelationalOperatorNode relationalOperatorNode;
    private SimpleExpressionNode rightSimpleExpressionNode;

    public ExpressionNode() throws SyntaxException {
        this.leftSimpleExpressionNode = null;
        this.relationalOperatorNode = null;
        this.rightSimpleExpressionNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.setLine(context.getLineCount());
        this.leftSimpleExpressionNode = new SimpleExpressionNode();
        this.leftSimpleExpressionNode.parse(context);
        if (context.equalsAny(0, "SEQUAL", "SNOTEQUAL", "SLESS", "SLESSEQUAL", "SGREAT", "SGREATEQUAL")) {
            this.relationalOperatorNode= new RelationalOperatorNode();
            this.relationalOperatorNode.parse(context);
            this.rightSimpleExpressionNode = new SimpleExpressionNode();
            this.rightSimpleExpressionNode.parse(context);
        }
    }

    public SimpleExpressionNode getLeftSimpleExpressionNode() {
        return this.leftSimpleExpressionNode;
    }

    public RelationalOperatorNode getRelationalOperatorNode() {
        return this.relationalOperatorNode;
    }

    public SimpleExpressionNode getRightSimpleExpressionNode() {
        return this.rightSimpleExpressionNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
