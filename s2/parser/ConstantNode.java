package enshud.s2.parser;

public class ConstantNode extends AstNode {
    private Token token;
    
    public ConstantNode() {
        this.token = null;
    }
    
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SCONSTANT", "SSTRING", "STRUE", "SFALSE");
    }

    public Token getToken() {
        return this.token;
    }
}