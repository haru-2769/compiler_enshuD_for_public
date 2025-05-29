package enshud.s2.parser;

public class IndexMinValueNode extends AstNode {
    private IntegerNode integerNode;

    public IndexMinValueNode() throws SyntaxException {
        this.integerNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.integerNode = new IntegerNode();
        this.integerNode.parse(context);
    }

    public IntegerNode getIntegerNode() {
        return this.integerNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}