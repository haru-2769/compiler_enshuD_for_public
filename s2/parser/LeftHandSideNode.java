package enshud.s2.parser;

public class LeftHandSideNode extends AstNode {
    private VariableNode variableNode;

    public LeftHandSideNode() throws SyntaxException {
        this.variableNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.variableNode = new VariableNode();
        this.variableNode.parse(context);
    }

    public VariableNode getVariableNode() {
        return this.variableNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}