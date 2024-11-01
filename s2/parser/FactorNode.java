package enshud.s2.parser;

public class FactorNode extends NonTerminalNode{
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER")) {
            VariableNode variableNode = new VariableNode();
            addChild(variableNode);
            variableNode.parse(context);
        } else if (context.equalsAny(0, "SCONSTANT", "SSTRING", "STRUE", "SFALSE")) {
            ConstantNode constantNode = new ConstantNode();
            addChild(constantNode);
            constantNode.parse(context);
        } else if (context.equalsAny(0, "SLPAREN")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SLPAREN")));
            ExpressionNode expressionNode = new ExpressionNode();
            addChild(expressionNode);
            expressionNode.parse(context);
            addChild(new TerminalNode(context.checkTerminalSymbol("SRPAREN")));
        } else {
            addChild(new TerminalNode(context.checkTerminalSymbol("SNOT")));
            FactorNode factorNode = new FactorNode();
            addChild(factorNode);
            factorNode.parse(context);
        }
    }
}