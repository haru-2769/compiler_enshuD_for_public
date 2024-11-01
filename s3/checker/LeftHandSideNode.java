package enshud.s3.checker;

public class LeftHandSideNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        VariableNode variableNode = new VariableNode();
        addChild(variableNode);
        variableNode.parse(context);
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}