package enshud.s2.parser;

public class MultiplicativeOperatorNode extends AstNode {
    private Token token;
    
    public MultiplicativeOperatorNode() throws SyntaxException {
        this.token = null;
    }
    
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SSTAR", "SDIVD", "SMOD", "SAND");
    }

    public Token getToken() {
        return this.token;
    }
}