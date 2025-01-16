package enshud.s4.compiler;

public class VariableNode extends ExprNode {
    private AstNode variableNode;
    private boolean isRightValue;

    public VariableNode() throws SyntaxException {
        this.variableNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(1, "SLBRACKET")) {
            this.variableNode = new IndexedVariableNode();
            this.variableNode.parse(context);
        } else {
            this.variableNode = new PureVariableNode();
            this.variableNode.parse(context);
        }
    }

    public void setIsRightValue(boolean isRightValue) {
        this.isRightValue = isRightValue;
    }
    
    public boolean isRightValue() {
        return this.isRightValue;
    }

    public AstNode getVariableNode() {
        return this.variableNode;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}