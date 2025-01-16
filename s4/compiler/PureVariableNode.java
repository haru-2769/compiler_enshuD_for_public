package enshud.s4.compiler;

public class PureVariableNode extends AstNode{
    private VariableNameNode variableNameNode;

    public PureVariableNode() throws SyntaxException {
        this.variableNameNode = null;
    }
    
    @Override
    public void parse(Context context) throws SyntaxException {
        this.variableNameNode = new VariableNameNode();
        this.variableNameNode.parse(context);
    }
    
    public VariableNameNode getVariableNameNode() {
        return this.variableNameNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}