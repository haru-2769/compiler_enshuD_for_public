package enshud.s2.parser;

public class StandardTypeNode extends NonTerminalNode {
    public StandardTypeNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN")));
    }
}