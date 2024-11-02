package enshud.s2.parser;

public class ProcedureCallStatementNode extends NonTerminalNode {
    public ProcedureCallStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new ProcedureNameNode(context));
        if (context.equalsAny(0, "SLPAREN")) {
            context.checkTerminalSymbol("SLPAREN");
            addChild(new ExpressionSequenceNode(context));
            context.checkTerminalSymbol("SRPAREN");
        }
    }
}