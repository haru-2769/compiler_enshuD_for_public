package enshud.s2.parser;

public class PureVariableNode extends NonTerminalNode{
    public PureVariableNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    protected void parse(Context context) throws SyntaxException {
        addChild(new VariableNameNode(context));
    }
}