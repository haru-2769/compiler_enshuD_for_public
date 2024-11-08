package enshud.s3.checker;

public class CompoundStatementNode extends AstNode {
    private StatementSequenceNode statementSequenceNode;

    public CompoundStatementNode() throws SyntaxException {
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

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}