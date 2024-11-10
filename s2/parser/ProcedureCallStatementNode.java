package enshud.s2.parser;

public class ProcedureCallStatementNode extends AstNode {
    private ProcedureNameNode procedureNameNode;
    private ExpressionSequenceNode expressionSequenceNode;

    public ProcedureCallStatementNode() {
        this.procedureNameNode = null;
        this.expressionSequenceNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.procedureNameNode = new ProcedureNameNode();
        this.procedureNameNode.parse(context);
        if (context.equalsAny(0, "SLPAREN")) {
            context.checkTerminalSymbol("SLPAREN");
            this.expressionSequenceNode = new ExpressionSequenceNode();
            this.expressionSequenceNode.parse(context);
            context.checkTerminalSymbol("SRPAREN");
        }
    }

    public ProcedureNameNode getProcedureNameNode() {
        return this.procedureNameNode;
    }

    public ExpressionSequenceNode getExpressionSequenceNode() {
        return this.expressionSequenceNode;
    }
}