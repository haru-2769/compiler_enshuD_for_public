package enshud.s3.checker;

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

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}