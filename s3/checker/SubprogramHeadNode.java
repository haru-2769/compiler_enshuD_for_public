package enshud.s3.checker;

public class SubprogramHeadNode extends NonTerminalNode {
    public SubprogramHeadNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SPROCEDURE")));
        addChild(new ProcedureNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
        addChild(new FormalParameterNode(context));
        addChild(new TerminalNode(context.checkTerminalSymbol("SSEMICOLON")));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}