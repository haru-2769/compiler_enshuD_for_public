package enshud.s2.parser;

public class IndexedVariableNode extends NonTerminalNode {
    public IndexedVariableNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new VariableNameNode(context));
        context.checkTerminalSymbol("SLBRACKET");
        addChild(new IndexNode(context));
        context.checkTerminalSymbol("SRBRACKET");
    }
}