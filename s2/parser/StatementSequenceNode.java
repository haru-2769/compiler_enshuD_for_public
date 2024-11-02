package enshud.s2.parser;

public class StatementSequenceNode extends NonTerminalNode{
    public StatementSequenceNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        do {
            addChild(new StatementNode(context));
            context.checkTerminalSymbol("SSEMICOLON");
        } while (context.equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN", "SIF", "SWHILE"));
    }
}