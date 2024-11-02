package enshud.s3.checker;

public class TypeNode extends NonTerminalNode {
    public TypeNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SINTEGER", "SCHAR", "SBOOLEAN")) {
            addChild(new StandardTypeNode(context.checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN")));
        } else if (context.equalsAny(0, "SARRAY")) {
            addChild(new ArrayTypeNode(context));
        } else {
            throw new SyntaxException(context.getTokenList().get(context.getIndex()));
        }
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
