package enshud.s2.parser;

public class TypeNode extends AstNode {
    private StandardTypeNode standardTypeNode;
    private ArrayTypeNode arrayTypeNode;

    public TypeNode() throws SyntaxException {
        this.standardTypeNode = null;
        this.arrayTypeNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SINTEGER", "SCHAR", "SBOOLEAN")) {
            standardTypeNode = new StandardTypeNode();
            standardTypeNode.parse(context);
        } else {
            arrayTypeNode = new ArrayTypeNode();
            arrayTypeNode.parse(context);
        }
    }
    
    public StandardTypeNode getStandardTypeNode() {
        return standardTypeNode;
    }

    public ArrayTypeNode getArrayTypeNode() {
        return arrayTypeNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
