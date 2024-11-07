package enshud.s3.checker;

import java.util.ArrayList;
import java.util.List;

public class StatementSequenceNode extends AstNode{
    private StatementNode statementNode;
    private List<StatementNode> statementNodes;

    public StatementSequenceNode() throws SyntaxException {
        this.statementNode = null;
        this.statementNodes = new ArrayList<>();
    }

    protected void parse(Context context) throws SyntaxException {
        do {
            this.statementNode = new StatementNode();
            this.statementNodes.add(statementNode);
            this.statementNode.parse(context);
            context.checkTerminalSymbol("SSEMICOLON");
        } while (context.equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN", "SIF", "SWHILE"));
    }

    public List<StatementNode> getStatementNodes() {
        return this.statementNodes;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}