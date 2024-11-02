package enshud.s2.parser;

public class VariableDeclarationSequenceNode extends NonTerminalNode {
    public VariableDeclarationSequenceNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        do {
            addChild(new VariableNameSequenceNode(context));
            context.checkTerminalSymbol("SCOLON");
            addChild(new TypeNode(context));
            context.checkTerminalSymbol("SSEMICOLON");
        } while (context.equalsAny(0, "SIDENTIFIER"));
    }
}