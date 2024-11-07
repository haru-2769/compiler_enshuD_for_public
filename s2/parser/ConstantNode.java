package enshud.s2.parser;

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
}