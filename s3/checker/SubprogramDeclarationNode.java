package enshud.s3.checker;

public class SubprogramDeclarationNode extends AstNode {
    private SubprogramHeadNode subprogramHeadNode;
    private VariableDeclarationNode variableDeclarationNode;
    private CompoundStatementNode compoundStatementNode;

    public SubprogramDeclarationNode() throws SyntaxException {
        this.subprogramHeadNode = null;
        this.variableDeclarationNode = null;
        this.compoundStatementNode = null;
    }

    protected void parse(Context context) throws SyntaxException {
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
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}