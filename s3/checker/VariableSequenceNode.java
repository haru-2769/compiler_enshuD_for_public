package enshud.s3.checker;

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

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}