package enshud.s2.parser;

public class InputOutputStatementNode extends NonTerminalNode {
    public InputOutputStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SREADLN")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SREADLN")));
            if (context.equalsAny(0, "SLPAREN")) {
                context.checkTerminalSymbol("SLPAREN");
                addChild(new VariableSequenceNode(context));
                context.checkTerminalSymbol("SRPAREN");
            }
        } else {
            addChild(new TerminalNode(context.checkTerminalSymbol("SWRITELN")));
            if (context.equalsAny(0, "SLPAREN")) {
                context.checkTerminalSymbol("SLPAREN");
                addChild(new ExpressionSequenceNode(context));
                context.checkTerminalSymbol("SRPAREN");
            }
        }
    }
}
