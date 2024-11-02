package enshud.s3.checker;

public class SubprogramHeadNode extends NonTerminalNode {
    public SubprogramHeadNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        context.checkTerminalSymbol("SPROCEDURE");
        addChild(new ProcedureNameNode(context));
        addChild(new FormalParameterNode(context));
        context.checkTerminalSymbol("SSEMICOLON");
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}