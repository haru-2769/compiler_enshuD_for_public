package enshud.s3.checker;

public class VariableNameSequenceNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        VariableNameNode variableNameNode = new VariableNameNode();
        addChild(variableNameNode);
        variableNameNode.parse(context);
        while (context.equalsAny(0, "SCOMMA")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SCOMMA")));
            VariableNameNode variableNameNode1 = new VariableNameNode();
            addChild(variableNameNode1);
            variableNameNode1.parse(context);
        }
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}