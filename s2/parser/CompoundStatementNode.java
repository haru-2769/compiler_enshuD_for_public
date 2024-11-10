package enshud.s2.parser;

public class CompoundStatementNode extends AstNode {
    private StatementSequenceNode statementSequenceNode;

    public CompoundStatementNode() {
        this.statementSequenceNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        context.checkTerminalSymbol("SBEGIN");
        this.statementSequenceNode = new StatementSequenceNode();
        this.statementSequenceNode.parse(context);
        context.checkTerminalSymbol("SEND");
    }

    public StatementSequenceNode getStatementSequenceNode() {
        return this.statementSequenceNode;
    }
}