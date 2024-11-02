package enshud.s2.parser;

public class IndexMinValueNode extends NonTerminalNode {
    public IndexMinValueNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new IntegerNode(context));
    }
}