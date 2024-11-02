package enshud.s3.checker;

public class IntegerNode extends NonTerminalNode {
    public IntegerNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SPLUS", "SMINUS")) {
            addChild(new SignNode(context));
        }
    	addChild(new TerminalNode(context.checkTerminalSymbol("SCONSTANT")));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}