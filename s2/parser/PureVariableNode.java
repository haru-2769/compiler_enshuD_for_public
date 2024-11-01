package enshud.s2.parser;

public class PureVariableNode extends NonTerminalNode{
    public void parse(Context context) throws SyntaxException {
        VariableNameNode variableNameNode = new VariableNameNode();
        addChild(variableNameNode);
        variableNameNode.parse(context);
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}