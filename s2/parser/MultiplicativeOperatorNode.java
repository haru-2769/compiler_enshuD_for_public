package enshud.s2.parser;

public class MultiplicativeOperatorNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SSTAR", "SDIVD", "SMOD", "SAND")));
    }
}