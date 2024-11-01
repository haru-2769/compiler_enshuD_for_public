package enshud.s2.parser;

public class AdditiveOperatorNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SPLUS", "SMINUS", "SOR")));
    }
}
