package enshud.s2.parser;

public class IntegerNode extends NonTerminalNode {
    public IntegerNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SPLUS", "SMINUS")) {
            addChild(new SignNode(context));
        }
    	addChild(new TerminalNode(context.checkTerminalSymbol("SCONSTANT")));
    }
}