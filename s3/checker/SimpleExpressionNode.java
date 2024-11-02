package enshud.s3.checker;

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
            addChild(new AdditiveOperatorNode(context.checkTerminalSymbol("SPLUS", "SMINUS", "SOR")));
            TermNode termNode1 = new TermNode();
            addChild(termNode1);
            termNode1.parse(context);
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}