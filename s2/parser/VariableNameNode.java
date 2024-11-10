package enshud.s2.parser;

public class VariableNameNode extends AstNode {
    private Token token;

    public VariableNameNode() {
        this.token = null;
    }
    
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SIDENTIFIER");
    }

    public Token getToken() {
        return this.token;
    }
}
