package enshud.s2.parser;

public class SubprogramDeclarationNode extends AstNode {
    private SubprogramHeadNode subprogramHeadNode;
    private VariableDeclarationNode variableDeclarationNode;
    private CompoundStatementNode compoundStatementNode;

    public SubprogramDeclarationNode() {
        this.subprogramHeadNode = null;
        this.variableDeclarationNode = null;
        this.compoundStatementNode = null;
    }   

    public void parse(Context context) throws SyntaxException {
        this.subprogramHeadNode = new SubprogramHeadNode();
        this.subprogramHeadNode.parse(context);
        this.variableDeclarationNode = new VariableDeclarationNode();
        this.variableDeclarationNode.parse(context);
        this.compoundStatementNode = new CompoundStatementNode();
        this.compoundStatementNode.parse(context);
    }

    public SubprogramHeadNode getSubprogramHeadNode() {
        return this.subprogramHeadNode;
    }

    public VariableDeclarationNode getVariableDeclarationNode() {
        return this.variableDeclarationNode;
    }

    public CompoundStatementNode getCompoundStatementNode() {
        return this.compoundStatementNode;
    }
}