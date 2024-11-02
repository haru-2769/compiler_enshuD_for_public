package enshud.s2.parser;

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
}