package enshud.s2.parser;

public class ArrayTypeNode extends NonTerminalNode {
    public ArrayTypeNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        context.checkTerminalSymbol("SARRAY");
        context.checkTerminalSymbol("SLBRACKET");
        addChild(new IndexMinValueNode(context));
        context.checkTerminalSymbol("SRANGE");
        addChild(new IndexMaxValueNode(context));
        context.checkTerminalSymbol("SRBRACKET");
        context.checkTerminalSymbol("SOF");
        addChild(new StandardTypeNode(context));
    }
}
