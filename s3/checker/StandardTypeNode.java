package enshud.s3.checker;

public class StandardTypeNode extends NonTerminalNode {
    public StandardTypeNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN")));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}