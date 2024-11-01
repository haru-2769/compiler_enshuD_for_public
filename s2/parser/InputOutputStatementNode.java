package enshud.s2.parser;

public class InputOutputStatementNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SREADLN")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SREADLN")));
            if (context.equalsAny(0, "SLPAREN")) {
                addChild(new TerminalNode(context.checkTerminalSymbol("SLPAREN")));
                VariableSequenceNode variableSequenceNode = new VariableSequenceNode();
                addChild(variableSequenceNode);
                variableSequenceNode.parse(context);
                addChild(new TerminalNode(context.checkTerminalSymbol("SRPAREN")));
            }
        } else {
            addChild(new TerminalNode(context.checkTerminalSymbol("SWRITELN")));
            if (context.equalsAny(0, "SLPAREN")) {
                addChild(new TerminalNode(context.checkTerminalSymbol("SLPAREN")));
                ExpressionSequenceNode expressionSequenceNode = new ExpressionSequenceNode();
                addChild(expressionSequenceNode);
                expressionSequenceNode.parse(context);
                addChild(new TerminalNode(context.checkTerminalSymbol("SRPAREN")));
            }
        }
    }
}
