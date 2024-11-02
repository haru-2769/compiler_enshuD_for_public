package enshud.s2.parser;

public class FactorNode extends NonTerminalNode{
    public FactorNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER")) {
            addChild(new VariableNode(context));
        } else if (context.equalsAny(0, "SCONSTANT", "SSTRING", "STRUE", "SFALSE")) {
            addChild(new ConstantNode(context));
        } else if (context.equalsAny(0, "SLPAREN")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SLPAREN")));
            addChild(new ExpressionNode(context));
            addChild(new TerminalNode(context.checkTerminalSymbol("SRPAREN")));
        } else {
            addChild(new TerminalNode(context.checkTerminalSymbol("SNOT")));
            addChild(new FactorNode(context));
        }
    }
}