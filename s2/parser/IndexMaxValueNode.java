package enshud.s2.parser;

public class IndexMaxValueNode extends AstNode {
    private IntegerNode integerNode;

    public IndexMaxValueNode() throws SyntaxException {
        this.integerNode = null;
    }

    protected void parse(Context context) throws SyntaxException {
        this.integerNode = new IntegerNode();
        this.integerNode.parse(context);
    }
    
    public IntegerNode getIntegerNode() {
        return this.integerNode;
    }
}