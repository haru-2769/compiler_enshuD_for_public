package enshud.s2.parser;

import java.util.ArrayList;
import java.util.List;

public class StatementSequenceNode extends AstNode{
    private StatementNode statementNode;
    private List<StatementNode> statementNodes;

    public StatementSequenceNode() {
        this.statementNode = null;
        this.statementNodes = new ArrayList<>();
    }

    public void parse(Context context) throws SyntaxException {
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
}