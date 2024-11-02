package enshud.s3.checker;

public class FormalParameterNode extends NonTerminalNode {
    public FormalParameterNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SLPAREN")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SLPAREN")));
            addChild(new FormalParameterSequenceNode(context));
            addChild(new TerminalNode(context.checkTerminalSymbol("SRPAREN")));
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}