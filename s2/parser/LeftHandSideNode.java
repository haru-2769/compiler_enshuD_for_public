package enshud.s2.parser;

public class LeftHandSideNode extends AstNode {
    private VariableNode variableNode;

    public LeftHandSideNode() {
        this.variableNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.variableNode = new VariableNode();
        this.variableNode.parse(context);
    }

    public VariableNode getVariableNode() {
        return this.variableNode;
    }
}