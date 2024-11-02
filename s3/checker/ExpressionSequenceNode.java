package enshud.s3.checker;

public class ExpressionSequenceNode extends NonTerminalNode {
    public ExpressionSequenceNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new ExpressionNode(context));
        while (context.equalsAny(0, "SCOMMA")) {
            context.checkTerminalSymbol("SCOMMA");
            addChild(new ExpressionNode(context));
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
