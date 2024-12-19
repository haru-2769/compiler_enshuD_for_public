package enshud.s4.compiler;

public class ExpressionNode extends AstNode {
    private Type type;
    private SimpleExpressionNode leftSimpleExpressionNode;
    private RelationalOperatorNode relationalOperatorNode;
    private SimpleExpressionNode rightSimpleExpressionNode;

    public ExpressionNode() throws SyntaxException {
        type = null;
        this.leftSimpleExpressionNode = null;
        this.relationalOperatorNode = null;
        this.rightSimpleExpressionNode = null;
    }

    public void parse(Context context) throws SyntaxException {
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

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
