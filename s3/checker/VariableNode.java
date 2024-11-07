package enshud.s3.checker;

public class VariableNode extends AstNode {
    private IndexedVariableNode indexedVariableNode;
    private PureVariableNode pureVariableNode;

    public VariableNode() throws SyntaxException {
        this.indexedVariableNode = null;
        this.pureVariableNode = null;
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(1, "SLBRACKET")) {
            this.indexedVariableNode = new IndexedVariableNode();
            this.indexedVariableNode.parse(context);
        } else {
            this.pureVariableNode = new PureVariableNode();
            this.pureVariableNode.parse(context);
        }
    }

    public IndexedVariableNode getIndexedVariableNode() {
        return this.indexedVariableNode;
    }

    public PureVariableNode getPureVariableNode() {
        return this.pureVariableNode;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}