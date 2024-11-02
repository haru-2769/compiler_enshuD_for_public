package enshud.s3.checker;

public class FormalParameterSequenceNode extends NonTerminalNode{
    public FormalParameterSequenceNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new FormalParameterNameSequenceNode(context));
        context.checkTerminalSymbol("SCOLON");
        addChild(new StandardTypeNode(context));
        while (context.equalsAny(0, "SSEMICOLON")) {
            context.checkTerminalSymbol("SSEMICOLON");
            addChild(new FormalParameterNameSequenceNode(context));
            context.checkTerminalSymbol("SCOLON");
            addChild(new StandardTypeNode(context));
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}