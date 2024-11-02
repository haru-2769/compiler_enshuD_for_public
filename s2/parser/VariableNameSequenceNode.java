package enshud.s2.parser;

public class VariableNameSequenceNode extends NonTerminalNode {
    public VariableNameSequenceNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    protected void parse(Context context) throws SyntaxException {
        addChild(new VariableNameNode(context));
        while (context.equalsAny(0, "SCOMMA")) {
            context.checkTerminalSymbol("SCOMMA");
            addChild(new VariableNameNode(context));
        }
    }
}