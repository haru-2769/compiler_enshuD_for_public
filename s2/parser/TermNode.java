package enshud.s2.parser;

public class TermNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        FactorNode factorNode = new FactorNode();
        addChild(factorNode);
        factorNode.parse(context);
        while (context.equalsAny(0, "SSTAR", "SDIVD", "SMOD", "SAND")) {
            MultiplicativeOperatorNode multiplicativeOperatorNode = new MultiplicativeOperatorNode();
            addChild(multiplicativeOperatorNode);
            multiplicativeOperatorNode.parse(context);
            FactorNode factorNode1 = new FactorNode();
            addChild(factorNode1);
            factorNode1.parse(context);
        }
    }
}