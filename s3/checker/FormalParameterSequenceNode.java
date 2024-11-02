package enshud.s3.checker;

public class FormalParameterSequenceNode extends NonTerminalNode{
    public FormalParameterSequenceNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new FormalParameterNameSequenceNode(context));
        addChild(new TerminalNode(context.checkTerminalSymbol("SCOLON")));
        addChild(new StandardTypeNode(context.checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN")));
        while (context.equalsAny(0, "SCOMMA")) {
            addChild(new FormalParameterNameSequenceNode(context));
            addChild(new TerminalNode(context.checkTerminalSymbol("SCOLON")));
            addChild(new StandardTypeNode(context.checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN")));
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}