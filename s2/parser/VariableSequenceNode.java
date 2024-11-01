package enshud.s2.parser;

public class VariableSequenceNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        VariableNode variableNode = new VariableNode();
        addChild(variableNode);
        variableNode.parse(context);
        while (context.equalsAny(0, "SCOMMA")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SCOMMA")));
            VariableNode variableNode1 = new VariableNode();
            addChild(variableNode1);
            variableNode1.parse(context);
        }
    }
}