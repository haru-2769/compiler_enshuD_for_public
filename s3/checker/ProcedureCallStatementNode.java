package enshud.s3.checker;

public class ProcedureCallStatementNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new ProcedureNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
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