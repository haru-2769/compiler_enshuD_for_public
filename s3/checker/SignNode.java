package enshud.s3.checker;

public class SignNode extends NonTerminalNode {
    public SignNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SPLUS", "SMINUS")));
    };
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}