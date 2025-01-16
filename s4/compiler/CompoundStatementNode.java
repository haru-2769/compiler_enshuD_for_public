package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.List;

public class CompoundStatementNode extends AstNode {
    private List<StatementNode> statementNodes;

    public CompoundStatementNode() throws SyntaxException {
        this.statementNodes = new ArrayList<>();
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        context.checkTerminalSymbol("SBEGIN");
        StatementNode statementNode;
        do {
            statementNode = new StatementNode();
            statementNode.parse(context);
            this.statementNodes.add(statementNode);
            context.checkTerminalSymbol("SSEMICOLON");
        } while (context.equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN", "SIF", "SWHILE"));
        context.checkTerminalSymbol("SEND");
    }
    
    public List<StatementNode> getStatementNodes() {
        return this.statementNodes;
    }

    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}