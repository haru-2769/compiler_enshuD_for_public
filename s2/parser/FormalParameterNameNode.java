package enshud.s2.parser;

public class FormalParameterNameNode extends AstNode{
    private Token token;

    public FormalParameterNameNode() throws SyntaxException {
        this.token = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SIDENTIFIER");
    }

    public Token getToken() {
        return this.token;
    }
}