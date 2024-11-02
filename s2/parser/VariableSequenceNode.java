package enshud.s2.parser;

public class VariableSequenceNode extends NonTerminalNode {
    public VariableSequenceNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new VariableNode(context));
        while (context.equalsAny(0, "SCOMMA")) {
            context.checkTerminalSymbol("SCOMMA");
            addChild(new VariableNode(context));
        }
    }
}