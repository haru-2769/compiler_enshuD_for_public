package enshud.s2.parser;

public class ExpressionNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        SimpleExpressionNode simpleExpressionNode = new SimpleExpressionNode();
        addChild(simpleExpressionNode);
        simpleExpressionNode.parse(context);
        if (context.equalsAny(0, "SEQUAL", "SNOTEQUAL", "SLESS", "SLESSEQUAL", "SGREAT", "SGREATEQUAL")) {
            RelationalOperatorNode relationalOperatorNode = new RelationalOperatorNode();
            addChild(relationalOperatorNode);
            relationalOperatorNode.parse(context);
            SimpleExpressionNode simpleExpressionNode1 = new SimpleExpressionNode();
            addChild(simpleExpressionNode1);
            simpleExpressionNode1.parse(context);
        }
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
