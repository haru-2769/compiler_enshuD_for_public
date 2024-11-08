package enshud.s2.parser;

public class IndexMinValueNode extends AstNode {
    private IntegerNode integerNode;

    public IndexMinValueNode() throws SyntaxException {
        this.integerNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.integerNode = new IntegerNode();
        this.integerNode.parse(context);
    }

    public IntegerNode getIntegerNode() {
        return this.integerNode;
    }
}