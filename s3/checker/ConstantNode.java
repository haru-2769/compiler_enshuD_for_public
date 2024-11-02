package enshud.s3.checker;

public class ConstantNode extends NonTerminalNode {
    public ConstantNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SCONSTANT", "SSTRING", "STRUE", "SFALSE")));
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}