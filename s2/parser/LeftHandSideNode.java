package enshud.s2.parser;

public class LeftHandSideNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        VariableNode variableNode = new VariableNode();
        addChild(variableNode);
        variableNode.parse(context);
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}