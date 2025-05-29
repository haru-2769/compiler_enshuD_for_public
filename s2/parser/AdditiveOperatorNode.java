package enshud.s2.parser;

public class AdditiveOperatorNode extends AstNode {
    @Override
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SPLUS", "SMINUS", "SOR");
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
