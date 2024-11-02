package enshud.s2.parser;

public class SimpleExpressionNode extends NonTerminalNode {
    public SimpleExpressionNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SPLUS", "SMINUS")) {
            addChild(new SignNode(context));
        }
        addChild(new TermNode(context));
        while (context.equalsAny(0, "SPLUS", "SMINUS", "SOR")) {
            addChild(new AdditiveOperatorNode(context));
            addChild(new TermNode(context));
        }
    }
}