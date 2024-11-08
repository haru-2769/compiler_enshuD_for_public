package enshud.s3.checker;

public class RelationalOperatorNode extends AstNode {
    private Token token;

    public RelationalOperatorNode() throws SyntaxException {
        this.token = null;
    }
    
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SEQUAL", "SNOTEQUAL", "SLESS", "SLESSEQUAL", "SGREAT", "SGREATEQUAL");
    }

    public Token getToken() {
        return this.token;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}