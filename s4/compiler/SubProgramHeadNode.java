package enshud.s4.compiler;

public class SubProgramHeadNode extends AstNode {
    private FormalParameterNode formalParameterNode;

    public SubProgramHeadNode() throws SyntaxException {
        this.formalParameterNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        context.checkTerminalSymbol("SPROCEDURE");
        this.token = context.checkTerminalSymbol("SIDENTIFIER");
        this.formalParameterNode = new FormalParameterNode();
        this.formalParameterNode.parse(context);
        context.checkTerminalSymbol("SSEMICOLON");
    }

    public FormalParameterNode getFormalParameterNode() {
        return this.formalParameterNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}