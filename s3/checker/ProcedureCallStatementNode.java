package enshud.s3.checker;

public class ProcedureCallStatementNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        ProcedureNameNode procedureNameNode = new ProcedureNameNode();
        addChild(procedureNameNode);
        procedureNameNode.parse(context);
        if (context.equalsAny(0, "SLPAREN")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SLPAREN")));
            ExpressionSequenceNode expressionSequenceNode = new ExpressionSequenceNode();
            addChild(expressionSequenceNode);
            expressionSequenceNode.parse(context);
            addChild(new TerminalNode(context.checkTerminalSymbol("SRPAREN")));
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}