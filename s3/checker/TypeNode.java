package enshud.s3.checker;

public class TypeNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SINTEGER", "SCHAR", "SBOOLEAN")) {
            StandardTypeNode standardTypeNode = new StandardTypeNode();
            addChild(standardTypeNode);
            standardTypeNode.parse(context);
        } else if (context.equalsAny(0, "SARRAY")) {
            ArrayTypeNode arrayTypeNode = new ArrayTypeNode();
            addChild(arrayTypeNode);
            arrayTypeNode.parse(context);
        } else {
            throw new SyntaxException(context.getTokenList().get(context.getIndex()));
        }
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
