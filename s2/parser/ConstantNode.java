package enshud.s2.parser;

public class ConstantNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SCONSTANT", "SSTRING", "STRUE", "SFALSE")));
    }
}