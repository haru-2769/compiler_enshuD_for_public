package enshud.s2.parser;

public class VariableNameNode extends NonTerminalNode {
    public VariableNameNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SIDENTIFIER")));
    }
}
