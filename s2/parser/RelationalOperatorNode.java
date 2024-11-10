package enshud.s2.parser;

public class RelationalOperatorNode extends AstNode {
    private Token token;

    public RelationalOperatorNode() {
        this.token = null;
    }
    
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SEQUAL", "SNOTEQUAL", "SLESS", "SLESSEQUAL", "SGREAT", "SGREATEQUAL");
    }

    public Token getToken() {
        return this.token;
    }
}