package enshud.s3.checker;

public class IndexMinValueNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        IntegerNode integerNode = new IntegerNode();
        addChild(integerNode);
        integerNode.parse(context);
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}