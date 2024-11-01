package enshud.s2.parser;

public class SimpleExpressionNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SPLUS", "SMINUS")) {
            SignNode signNode = new SignNode();
            addChild(signNode);
            signNode.parse(context);
        }
        TermNode termNode = new TermNode();
        addChild(termNode);
        termNode.parse(context);
        while (context.equalsAny(0, "SPLUS", "SMINUS", "SOR")) {
            AdditiveOperatorNode additiveOperatorNode = new AdditiveOperatorNode();
            addChild(additiveOperatorNode);
            additiveOperatorNode.parse(context);
            TermNode termNode1 = new TermNode();
            addChild(termNode1);
            termNode1.parse(context);
        }
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}