package enshud.s2.parser;

public class ExpressionNode extends NonTerminalNode {
    public ExpressionNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new SimpleExpressionNode(context));
        if (context.equalsAny(0, "SEQUAL", "SNOTEQUAL", "SLESS", "SLESSEQUAL", "SGREAT", "SGREATEQUAL")) {
            addChild(new RelationalOperatorNode(context));
            addChild(new SimpleExpressionNode(context));
        }
    }
}
