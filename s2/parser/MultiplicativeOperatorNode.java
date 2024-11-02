package enshud.s2.parser;

public class MultiplicativeOperatorNode extends NonTerminalNode {
    public MultiplicativeOperatorNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SSTAR", "SDIVD", "SMOD", "SAND")));
    }
}