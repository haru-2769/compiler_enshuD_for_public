package enshud.s2.parser;

public class CompoundStatementNode extends AstNode {
    private StatementSequenceNode statementSequenceNode;

    public CompoundStatementNode() throws SyntaxException {
        this.statementSequenceNode = null;
    }

    protected void parse(Context context) throws SyntaxException {
        context.checkTerminalSymbol("SBEGIN");
        this.statementSequenceNode = new StatementSequenceNode();
        this.statementSequenceNode.parse(context);
        context.checkTerminalSymbol("SEND");
    }

    public StatementSequenceNode getStatementSequenceNode() {
        return this.statementSequenceNode;
    }
}