package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.List;

public class ProcedureCallStatementNode extends StmtNode {
    private boolean isGlobal;
    private List<ExpressionNode> expressionNodes;

    public ProcedureCallStatementNode() throws SyntaxException {
        this.expressionNodes = new ArrayList<ExpressionNode>();
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.setLine(context.getLineCount());
        this.token =context.checkTerminalSymbol("SIDENTIFIER");
        if (context.equalsAny(0, "SLPAREN")) {
            ExpressionNode expressionNode;
            context.checkTerminalSymbol("SLPAREN");
            while (true) {
                expressionNode = new ExpressionNode();
                expressionNode.parse(context);
                this.expressionNodes.add(expressionNode);
                if (context.equalsAny(0, "SCOMMA")) {
                    context.checkTerminalSymbol("SCOMMA");
                } else {
                    break;
                }
            }
            context.checkTerminalSymbol("SRPAREN");
        }
    }

    public void setGlobal(boolean isGlobal) {
        this.isGlobal = isGlobal;
    }

    public boolean isGlobal() {
        return this.isGlobal;
    }

    public List<ExpressionNode> getExpressionNodes() {
        return this.expressionNodes;
    }

    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}