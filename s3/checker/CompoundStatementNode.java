package enshud.s3.checker;

public class CompoundStatementNode extends NonTerminalNode {
    public CompoundStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SBEGIN")));
        addChild(new StatementSequenceNode(context));
        addChild(new TerminalNode(context.checkTerminalSymbol("SEND")));
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}