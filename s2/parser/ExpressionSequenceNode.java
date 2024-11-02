package enshud.s2.parser;

public class ExpressionSequenceNode extends NonTerminalNode {
    public ExpressionSequenceNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new ExpressionNode(context));
        while (context.equalsAny(0, "SCOMMA")) {
            context.checkTerminalSymbol("SCOMMA");
            addChild(new ExpressionNode(context));
        }
    }
}
