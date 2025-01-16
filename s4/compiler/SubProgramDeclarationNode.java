package enshud.s4.compiler;

public class SubProgramDeclarationNode extends AstNode {
    private SubProgramHeadNode subProgramHeadNode;
    private VariableDeclarationNode variableDeclarationNode;
    private CompoundStatementNode compoundStatementNode;

    public SubProgramDeclarationNode() throws SyntaxException {
        this.subProgramHeadNode = null;
        this.variableDeclarationNode = null;
        this.compoundStatementNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.subProgramHeadNode = new SubProgramHeadNode();
        this.subProgramHeadNode.parse(context);
        this.variableDeclarationNode = new VariableDeclarationNode();
        this.variableDeclarationNode.parse(context);
        this.compoundStatementNode = new CompoundStatementNode();
        this.compoundStatementNode.parse(context);
    }

    public SubProgramHeadNode getSubProgramHeadNode() {
        return this.subProgramHeadNode;
    }

    public VariableDeclarationNode getVariableDeclarationNode() {
        return this.variableDeclarationNode;
    }

    public CompoundStatementNode getCompoundStatementNode() {
        return this.compoundStatementNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}