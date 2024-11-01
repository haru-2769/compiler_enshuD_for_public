package enshud.s2.parser;

public class IndexMaxValueNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        IntegerNode integerNode = new IntegerNode();
        addChild(integerNode);
        integerNode.parse(context);
    }
}