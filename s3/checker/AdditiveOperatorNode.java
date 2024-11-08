package enshud.s3.checker;

public class AdditiveOperatorNode extends AstNode {
    private Token token;
    
    public AdditiveOperatorNode() throws SyntaxException {
        this.token = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SPLUS", "SMINUS", "SOR");
    }

    public Token getToken() {
        return this.token;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
