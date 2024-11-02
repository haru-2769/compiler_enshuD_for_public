package enshud.s3.checker;

public class BlockNode extends AstNode {
    private VariableDeclarationNode variableDeclarationNode;
    private SubprogramDeclarationSequenceNode subprogramDeclarationSequenceNode;

    public BlockNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        variableDeclarationNode = new VariableDeclarationNode(context);
        subprogramDeclarationSequenceNode = new SubprogramDeclarationSequenceNode(context);
    }

    public VariableDeclarationNode getVariableDeclarationNode() {
        return variableDeclarationNode;
    }

    public SubprogramDeclarationSequenceNode getSubprogramDeclarationSequenceNode() {
        return subprogramDeclarationSequenceNode;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
