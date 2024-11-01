package enshud.s3.checker;

public class SignNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SPLUS", "SMINUS")));
    };
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}