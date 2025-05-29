package enshud.s2.parser;

public class SignNode extends AstNode {
    @Override
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SPLUS", "SMINUS");
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}