package enshud.s4.compiler;

public class BlockNode extends AstNode {
    private VariableDeclarationNode variableDeclarationNode;
    private SubProgramDeclarationSequenceNode subProgramDeclarationSequenceNode;

    public BlockNode() throws SyntaxException {
        this.variableDeclarationNode = null;
        this.subProgramDeclarationSequenceNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.variableDeclarationNode = new VariableDeclarationNode();
        this.variableDeclarationNode.parse(context);
        this.subProgramDeclarationSequenceNode = new SubProgramDeclarationSequenceNode();
        this.subProgramDeclarationSequenceNode.parse(context);
    }

    public VariableDeclarationNode getVariableDeclarationNode() {
        return this.variableDeclarationNode;
    }

    public SubProgramDeclarationSequenceNode getSubProgramDeclarationSequenceNode() {
        return this.subProgramDeclarationSequenceNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
