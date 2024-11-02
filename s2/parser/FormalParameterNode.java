package enshud.s2.parser;

public class FormalParameterNode extends NonTerminalNode {
    public FormalParameterNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SLPAREN")) {
            context.checkTerminalSymbol("SLPAREN");
            addChild(new FormalParameterSequenceNode(context));
            context.checkTerminalSymbol("SRPAREN");
        }
    }
}