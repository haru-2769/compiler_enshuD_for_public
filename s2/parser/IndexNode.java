package enshud.s2.parser;

public class IndexNode extends NonTerminalNode {
    public IndexNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new ExpressionNode(context));
    }
}