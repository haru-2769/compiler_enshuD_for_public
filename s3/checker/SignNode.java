package enshud.s3.checker;

public class SignNode extends AstNode {
    private Token token;

    public SignNode() throws SyntaxException {
        this.token = null;
    }
    
    protected void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SPLUS", "SMINUS");
    }

    public Token getToken() {
        return this.token;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}