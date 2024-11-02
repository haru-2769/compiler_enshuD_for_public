package enshud.s3.checker;

public class LeftHandSideNode extends NonTerminalNode {
    public LeftHandSideNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new VariableNode(context));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}