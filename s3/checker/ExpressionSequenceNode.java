package enshud.s3.checker;

public class ExpressionSequenceNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        ExpressionNode expressionNode = new ExpressionNode();
        addChild(expressionNode);
        expressionNode.parse(context);
        while (context.equalsAny(0, "SCOMMA")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SCOMMA")));
            ExpressionNode expressionNode1 = new ExpressionNode();
            addChild(expressionNode1);
            expressionNode1.parse(context);
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
