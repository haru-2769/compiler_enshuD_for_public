package enshud.s2.parser;

public class ConstantNode extends NonTerminalNode {
    public ConstantNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SCONSTANT", "SSTRING", "STRUE", "SFALSE")));
    }
}