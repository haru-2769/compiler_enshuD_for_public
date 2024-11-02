package enshud.s3.checker;

public class ProcedureCallStatementNode extends NonTerminalNode {
    public ProcedureCallStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new ProcedureNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
        if (context.equalsAny(0, "SLPAREN")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SLPAREN")));
            addChild(new ExpressionSequenceNode(context));
            addChild(new TerminalNode(context.checkTerminalSymbol("SRPAREN")));
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}