package enshud.s2.parser;

public class PureVariableNode extends AstNode{
    private VariableNameNode variableNameNode;

    public PureVariableNode() {
        this.variableNameNode = null;
    }
    
    public void parse(Context context) throws SyntaxException {
        this.variableNameNode = new VariableNameNode();
        this.variableNameNode.parse(context);
    }

    public VariableNameNode getVariableNameNode() {
        return this.variableNameNode;
    }
}