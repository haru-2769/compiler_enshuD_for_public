package enshud.s2.parser;

public class SignNode extends NonTerminalNode {
    public SignNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SPLUS", "SMINUS")));
    };
}