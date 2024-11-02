package enshud.s3.checker;

public class StatementSequenceNode extends NonTerminalNode{
    public StatementSequenceNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        do {
            addChild(new StatementNode(context));
            addChild(new TerminalNode(context.checkTerminalSymbol("SSEMICOLON")));
        } while (context.equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN", "SIF", "SWHILE"));
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}