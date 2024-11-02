package enshud.s2.parser;

public class StatementNode extends NonTerminalNode{
    public StatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN")) {
            addChild(new BasicStatementNode(context));
        } else if (context.equalsAny(0, "SIF")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SIF")));
            addChild(new ExpressionNode(context));
            addChild(new TerminalNode(context.checkTerminalSymbol("STHEN")));
            addChild(new CompoundStatementNode(context));
            if (context.equalsAny(0, "SELSE")) {
                addChild(new TerminalNode(context.checkTerminalSymbol("SELSE")));
                addChild(new CompoundStatementNode(context));
            }
        } else {
            addChild(new TerminalNode(context.checkTerminalSymbol("SWHILE")));
            addChild(new ExpressionNode(context));
            addChild(new TerminalNode(context.checkTerminalSymbol("SDO")));
            addChild(new CompoundStatementNode(context));
        }
    }
}