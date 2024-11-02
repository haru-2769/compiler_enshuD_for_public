package enshud.s2.parser;

public class LeftHandSideNode extends NonTerminalNode {
    public LeftHandSideNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new VariableNode(context));
    }
}