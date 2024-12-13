package enshud.s4.compiler;

public class SubprogramHeadNode extends AstNode {
    private ProcedureNameNode procedureNameNode;
    private FormalParameterNode formalParameterNode;

    public SubprogramHeadNode() throws SyntaxException {
        this.procedureNameNode = null;
        this.formalParameterNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        context.checkTerminalSymbol("SPROCEDURE");
        this.procedureNameNode = new ProcedureNameNode();
        this.procedureNameNode.parse(context);
        this.formalParameterNode = new FormalParameterNode();
        this.formalParameterNode.parse(context);
        context.checkTerminalSymbol("SSEMICOLON");
    }

    public ProcedureNameNode getProcedureNameNode() {
        return this.procedureNameNode;
    }

    public FormalParameterNode getFormalParameterNode() {
        return this.formalParameterNode;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}