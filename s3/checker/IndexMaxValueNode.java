package enshud.s3.checker;

public class IndexMaxValueNode extends NonTerminalNode {
    public IndexMaxValueNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new IntegerNode(context));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}