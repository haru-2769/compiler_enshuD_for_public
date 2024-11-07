package enshud.s3.checker;

public class ConstantNode extends AstNode {
    private Token token;
    
    public ConstantNode() throws SyntaxException {
        this.token = null;
    }
    
    protected void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SCONSTANT", "SSTRING", "STRUE", "SFALSE");
    }

    public Token getToken() {
        return this.token;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}