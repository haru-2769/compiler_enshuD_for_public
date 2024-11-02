package enshud.s2.parser;

public class VariableNode extends NonTerminalNode {
    public VariableNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(1, "SLBRACKET")) {
            addChild(new IndexedVariableNode(context));
        } else {
            addChild(new PureVariableNode(context));
        }
    }
}