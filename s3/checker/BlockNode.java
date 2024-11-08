package enshud.s3.checker;

public class BlockNode extends AstNode {
    private VariableDeclarationNode variableDeclarationNode;
    private SubprogramDeclarationSequenceNode subprogramDeclarationSequenceNode;

    public BlockNode() throws SyntaxException {
        this.variableDeclarationNode = null;
        this.subprogramDeclarationSequenceNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.variableDeclarationNode = new VariableDeclarationNode();
        this.variableDeclarationNode.parse(context);
        this.subprogramDeclarationSequenceNode = new SubprogramDeclarationSequenceNode();
        this.subprogramDeclarationSequenceNode.parse(context);
    }

    public VariableDeclarationNode getVariableDeclarationNode() {
        return this.variableDeclarationNode;
    }

    public SubprogramDeclarationSequenceNode getSubprogramDeclarationSequenceNode() {
        return this.subprogramDeclarationSequenceNode;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
