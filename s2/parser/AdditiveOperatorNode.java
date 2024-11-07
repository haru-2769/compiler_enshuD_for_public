package enshud.s2.parser;

public class AdditiveOperatorNode extends AstNode {
    private Token token;
    public AdditiveOperatorNode() throws SyntaxException {
        this.token = null;
    }

    protected void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SPLUS", "SMINUS", "SOR");
    }

    public Token getToken() {
        return this.token;
    }
}
