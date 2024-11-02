package enshud.s3.checker;
public class IndexNode extends NonTerminalNode {
    public IndexNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new ExpressionNode(context));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}