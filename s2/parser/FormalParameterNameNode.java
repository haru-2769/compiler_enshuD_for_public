package enshud.s2.parser;

public class FormalParameterNameNode extends NonTerminalNode{
    public FormalParameterNameNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SIDENTIFIER")));
    }
}