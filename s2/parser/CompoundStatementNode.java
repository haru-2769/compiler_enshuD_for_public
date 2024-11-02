package enshud.s2.parser;

public class CompoundStatementNode extends NonTerminalNode {
    public CompoundStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        context.checkTerminalSymbol("SBEGIN");
        addChild(new StatementSequenceNode(context));
        context.checkTerminalSymbol("SEND");
    }
}