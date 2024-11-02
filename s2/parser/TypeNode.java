package enshud.s2.parser;

public class TypeNode extends NonTerminalNode {
    public TypeNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SINTEGER", "SCHAR", "SBOOLEAN")) {
            addChild(new StandardTypeNode(context));
        } else {
            addChild(new ArrayTypeNode(context));
        }
    }
}
