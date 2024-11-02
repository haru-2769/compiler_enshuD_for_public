package enshud.s2.parser;

public class TermNode extends NonTerminalNode {
    public TermNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new FactorNode(context));
        while (context.equalsAny(0, "SSTAR", "SDIVD", "SMOD", "SAND")) {
            addChild(new MultiplicativeOperatorNode(context));
            addChild(new FactorNode(context));
        }
    }
}