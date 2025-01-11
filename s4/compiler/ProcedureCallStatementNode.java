package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.List;

public class ProcedureCallStatementNode extends StmtNode {
    private ProcedureNameNode procedureNameNode;
    private List<ExpressionNode> expressionNodes;

    public ProcedureCallStatementNode() throws SyntaxException {
        this.procedureNameNode = null;
        this.expressionNodes = new ArrayList<ExpressionNode>();
    }

    public void parse(Context context) throws SyntaxException {
        this.procedureNameNode = new ProcedureNameNode();
        this.procedureNameNode.parse(context);
        this.setLine(this.procedureNameNode.getToken().getLineCount());
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

    public ProcedureNameNode getProcedureNameNode() {
        return this.procedureNameNode;
    }

    public List<ExpressionNode> getExpressionNodes() {
        return this.expressionNodes;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}