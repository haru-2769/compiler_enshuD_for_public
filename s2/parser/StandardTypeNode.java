package enshud.s2.parser;

public class StandardTypeNode extends AstNode {
    private Token token;

    public StandardTypeNode() {
        this.token = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN");
    }

    public Token getToken() {
        return this.token;
    }
}