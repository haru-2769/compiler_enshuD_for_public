package enshud.s2.parser;

public class IndexMaxValueNode extends NonTerminalNode {
    public IndexMaxValueNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new IntegerNode(context));
    }
}